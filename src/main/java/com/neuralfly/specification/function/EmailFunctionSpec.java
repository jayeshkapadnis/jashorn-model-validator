package com.neuralfly.specification.function;

import lombok.NoArgsConstructor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@NoArgsConstructor
public class EmailFunctionSpec extends FunctionSpec {
    @Override
    public String name() {
        return "email";
    }

    @Override
    public boolean validate(String data, String fieldName, ScriptEngine engine) throws ScriptException, NoSuchMethodException {
        Invocable scriptEngine = (Invocable) engine;
        Object result = scriptEngine.invokeFunction("checkEmail", data, fieldName);
        return (Boolean)result;
    }
}
