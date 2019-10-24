package com.neuralfly.specification.function;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Getter
@Setter
@ToString
public class RequiredFunctionSpec extends FunctionSpec {
    @Override
    public String name() {
        return "required";
    }

    @Override
    public boolean validate(String data, String fieldName, ScriptEngine engine) throws ScriptException, NoSuchMethodException{
        Invocable scriptEngine = (Invocable) engine;
        Object result = scriptEngine.invokeFunction("checkRequired", data, fieldName);
        return (Boolean)result;
    }
}
