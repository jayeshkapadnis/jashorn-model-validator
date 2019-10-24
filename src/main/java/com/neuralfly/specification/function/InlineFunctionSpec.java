package com.neuralfly.specification.function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InlineFunctionSpec extends FunctionSpec{
    private String function;
    private String functionName;

    @Override
    public String name() {
        return "inline";
    }

    @Override
    public boolean validate(String data, String fieldName, ScriptEngine engine) throws ScriptException, NoSuchMethodException {
        if(function == null || "".equals(function)) return false;
        engine.eval(getFunction());
        Invocable scriptEngine = (Invocable) engine;
        Object result = scriptEngine.invokeFunction(getFunctionName(), data, fieldName);
        return (Boolean)result;
    }
}
