package com.neuralfly.specification.function;

import com.neuralfly.scripts.ScriptEngineFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

public class RequiredFunctionSpecTest {

    private static ScriptEngine engine;

    @BeforeClass
    public static void init() throws IOException, ScriptException {
        engine = ScriptEngineFactory.newScriptEngine();
    }

    @Test
    public void shouldReturnTrueIfCheckPassed() throws ScriptException, NoSuchMethodException {
        RequiredFunctionSpec functionSpec = new RequiredFunctionSpec();
        String data = "{\"data\": { \"someData\": { \"num\": 3} }}";

        boolean result = functionSpec.validate(data, "data.someData.num", engine);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfCheckFailed() throws ScriptException, NoSuchMethodException {
        RequiredFunctionSpec functionSpec = new RequiredFunctionSpec();
        String data = "{\"data\": { \"someData\": { \"num\": null} }}";

        boolean result = functionSpec.validate(data, "data.someData.num", engine);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfFieldNotPresent() throws ScriptException, NoSuchMethodException {
        RequiredFunctionSpec functionSpec = new RequiredFunctionSpec();
        String data = "{\"data\": { \"someData\": { \"count\": 3} }}";

        boolean result = functionSpec.validate(data, "data.someData.num", engine);

        assertFalse(result);
    }
}
