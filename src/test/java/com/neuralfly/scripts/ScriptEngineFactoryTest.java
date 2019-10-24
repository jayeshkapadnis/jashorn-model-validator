package com.neuralfly.scripts;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

public class ScriptEngineFactoryTest {

    @Test
    public void shouldReturnScriptEngineInstance() throws IOException, ScriptException {
        ScriptEngine nashorn = ScriptEngineFactory.newScriptEngine();
        assertNotNull(nashorn);
    }

    @Test
    public void engineShouldExecuteCheckEmailFunctionAndReturnTrue() throws IOException, ScriptException, NoSuchMethodException {
        String data = "{\"email\": \"jayesh.kapadnis@gmail.com\"}";
        ScriptEngine nashorn = ScriptEngineFactory.newScriptEngine();
        Invocable invocable = (Invocable) nashorn;
        Object result = invocable.invokeFunction("checkEmail", data, "email");
        assertTrue((Boolean) result);
    }

    @Test
    public void engineShouldExecuteCheckEmailFunctionAndReturnFalse() throws ScriptException, NoSuchMethodException, IOException {
        String data = "{\"email\": \"jayesh.kapadnis@gmail\"}";
        ScriptEngine nashorn = ScriptEngineFactory.newScriptEngine();
        Invocable invocable = (Invocable) nashorn;
        Object result = invocable.invokeFunction("checkEmail", data, "email");
        assertFalse((Boolean) result);
    }

    @Test
    public void engineShouldCheckNumberField() throws IOException, ScriptException, NoSuchMethodException {
        String data = "{\"num\": 33}";
        ScriptEngine nashorn = ScriptEngineFactory.newScriptEngine();
        Invocable invocable = (Invocable) nashorn;
        Object result = invocable.invokeFunction("checkNumber", data, "num");
        assertTrue((Boolean) result);
    }

    @Test
    public void engineShouldCheckInvalidNumberField() throws IOException, ScriptException, NoSuchMethodException {
        String data = "{\"num\": \"abc\"}";
        ScriptEngine nashorn = ScriptEngineFactory.newScriptEngine();
        Invocable invocable = (Invocable) nashorn;
        Object result = invocable.invokeFunction("checkNumber", data, "num");
        assertFalse((Boolean) result);
    }

    @Test
    public void engineShouldEvaluateMatchPattern() throws IOException, ScriptException, NoSuchMethodException {
        String data = "{\"num\": 3}";
        ScriptEngine nashorn = ScriptEngineFactory.newScriptEngine();
        Invocable invocable = (Invocable) nashorn;
        Object result = invocable.invokeFunction("matchPattern", data, "num", "\\d+");
        assertTrue((Boolean) result);
    }
}
