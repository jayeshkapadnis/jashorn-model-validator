package com.neuralfly.specification;

import com.neuralfly.scripts.ScriptEngineFactory;
import com.neuralfly.specification.validation.ValidationResult;
import lombok.*;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Specification {
    private String identifier;
    private List<Field> fields;

    protected ScriptEngine buildScriptEngine() throws IOException, ScriptException {
        return ScriptEngineFactory.newScriptEngine();
    }

    public Map<String, List<ValidationResult>> execute(String data) throws IOException, ScriptException, NoSuchMethodException {
        ScriptEngine engine = buildScriptEngine();
        Map<String, List<ValidationResult>> fieldResults = new HashMap<>();
        for (Field field: fields){
            fieldResults.put(field.getName(), field.validateFunctions(data, engine));
        }
        return fieldResults;
    }
}
