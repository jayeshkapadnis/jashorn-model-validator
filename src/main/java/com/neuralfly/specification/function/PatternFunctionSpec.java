package com.neuralfly.specification.function;

import lombok.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PatternFunctionSpec extends FunctionSpec{
    private String pattern;

    @Override
    public String name() {
        return "pattern";
    }

    @Override
    public boolean validate(String data, String fieldName, ScriptEngine engine) throws ScriptException, NoSuchMethodException {
        Invocable scriptEngine = (Invocable) engine;
        Object result = scriptEngine.invokeFunction("matchPattern", data, fieldName, pattern);
        return (Boolean)result;
    }
}
