package com.neuralfly.specification.function;

import com.neuralfly.scripts.ScriptEngineFactory;
import org.junit.Test;

import javax.script.ScriptException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PatternFunctionSpecTest {

    @Test
    public void shouldValidateDataWithPattern() throws IOException, ScriptException, NoSuchMethodException {
        PatternFunctionSpec patternSpec = new PatternFunctionSpec("\\d+");
        String data = "{\"field\": 33}";

        boolean result = patternSpec.validate(data, "field", ScriptEngineFactory.newScriptEngine());

        assertTrue(result);
    }

    @Test
    public void shouldValidateInvalidDataWithPattern() throws IOException, ScriptException, NoSuchMethodException {
        PatternFunctionSpec patternSpec = new PatternFunctionSpec("\\d+");
        String data = "{\"field\": \"abc\"}";

        boolean result = patternSpec.validate(data, "field", ScriptEngineFactory.newScriptEngine());

        assertFalse(result);
    }

    @Test
    public void shouldValidatedNestedField() throws IOException, ScriptException, NoSuchMethodException {
        PatternFunctionSpec patternSpec = new PatternFunctionSpec("\\d+");
        String data = "{\"field\": {\"num\": 123}}";

        boolean result = patternSpec.validate(data, "field.num", ScriptEngineFactory.newScriptEngine());

        assertTrue(result);
    }

    @Test
    public void shouldInvalidatedNestedField() throws IOException, ScriptException, NoSuchMethodException {
        PatternFunctionSpec patternSpec = new PatternFunctionSpec("\\d+");
        String data = "{\"field\": {\"num\": \"abc\"}}";

        boolean result = patternSpec.validate(data, "field.num", ScriptEngineFactory.newScriptEngine());

        assertFalse(result);
    }
}
