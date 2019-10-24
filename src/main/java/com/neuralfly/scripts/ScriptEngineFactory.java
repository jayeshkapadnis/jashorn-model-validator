package com.neuralfly.scripts;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.net.URL;

public class ScriptEngineFactory {

    public static ScriptEngine newScriptEngine() throws IOException, ScriptException {
        ScriptEngine nashorn = new ScriptEngineManager().getEngineByName("javascript");
        URL functions = Resources.getResource("functions.js");
        nashorn.eval(Resources.toString(functions, Charsets.UTF_8));
        return nashorn;
    }
}
