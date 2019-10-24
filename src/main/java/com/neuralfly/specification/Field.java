package com.neuralfly.specification;

import com.neuralfly.specification.function.FunctionSpec;
import com.neuralfly.specification.validation.ValidationResult;
import lombok.*;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Field {
    private String name;
    private String type;
    private List<FunctionSpec> functionSpecs;

    public List<ValidationResult> validateFunctions(String data, ScriptEngine engine) throws ScriptException, NoSuchMethodException {
        List<ValidationResult> results = new ArrayList<>();
        String errorFormat = "Failed Validation, field: %s, function: %s";
        String successFormat = "Validation Successful, field: %s, function: %s";
        for(FunctionSpec function: functionSpecs){
            boolean result = function.validate(data, getName(), engine);
            if(result) results.add(new ValidationResult("success", String.format(successFormat, getName(), function.name())));
            else results.add(new ValidationResult("failure", String.format(errorFormat, getName(), function.name())));
        }
        return results;
    }
}
