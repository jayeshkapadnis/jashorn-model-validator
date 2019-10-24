package com.neuralfly.specification.function;

import com.neuralfly.scripts.ScriptEngineFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InlineFunctionSpecTest {
    private static String function;
    private static ScriptEngine engine;

    @BeforeClass
    public static void init() throws IOException, ScriptException {
        function = "var customFunction = function(data, fieldName){ " +
                "return (checkRequired(data, 'email') && checkEmail(data, 'email')) || checkRequired(data, 'mobile');" +
                "}";
        engine = ScriptEngineFactory.newScriptEngine();
    }

    @Test
    public void shouldExecuteProvidedFunctionSuccessfully() throws IOException, ScriptException, NoSuchMethodException {
        InlineFunctionSpec inlineFunction = new InlineFunctionSpec(function, "customFunction");
        String data = "{\"email\": \"jayesh.kapadnis@gmail.com\"}";

        boolean result = inlineFunction.validate(data, "", engine);
        assertTrue(result);
    }

    @Test
    public void invalidateOnIncorrectInput() throws ScriptException, NoSuchMethodException {
        InlineFunctionSpec inlineFunction = new InlineFunctionSpec(function, "customFunction");
        String data = "{\"name\": \"jayesh\"}";

        boolean result = inlineFunction.validate(data, "", engine);

        assertFalse(result);
    }
}
