package com.neuralfly.specification.function;

import com.neuralfly.scripts.ScriptEngineFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailFunctionSpecTest {

    private static ScriptEngine engine;
    private EmailFunctionSpec function = new EmailFunctionSpec();

    @BeforeClass
    public static void init() throws IOException, ScriptException {
        engine = ScriptEngineFactory.newScriptEngine();
    }

    @Test
    public void shouldReturnTrueIfValidEmail() throws ScriptException, NoSuchMethodException {
        String data = "{\"customer\": { \"email\": \"abc@xyz.com\"} }";

        boolean result = function.validate(data, "customer.email", engine);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseIfInvalidEmail() throws ScriptException, NoSuchMethodException {
        String data = "{\"customer\": { \"email\": \"abc.xyz.com\"} }";

        boolean result = function.validate(data, "customer.email", engine);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfNoEmail() throws ScriptException, NoSuchMethodException {
        String data = "{\"customer\": { \"name\": \"abc.xyz.com\"} }";

        boolean result = function.validate(data, "customer.email", engine);

        assertFalse(result);
    }
}
