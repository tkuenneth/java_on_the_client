package com.thomaskuenneth.scriptingdemo;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Thomas Kuenneth
 */
public class ScriptingDemo {

    private static final String ECMA_SCRIPT = "ECMAScript";
    private static final Logger LOGGER = Logger.getLogger(ScriptingDemo.class.getName());

    public static void main(String[] args) {
        ScriptEngineManager m = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = m.getEngineFactories();
        factories.stream().map((factory) -> {
            System.out.println(String.format("%s (%s %s)", factory.getLanguageName(),
                    factory.getEngineName(),
                    factory.getEngineVersion()));
            return factory;
        }).filter((factory) -> (factory.getLanguageName().contains(ECMA_SCRIPT))).forEachOrdered((factory) -> {
            ScriptEngine engine = factory.getScriptEngine();
            String script = factory.getOutputStatement("\"Hello " + ECMA_SCRIPT + "\"");
            try {
                Object result = engine.eval(script);
                if (result != null) {
                    System.out.println(result);
                }
            } catch (ScriptException ex) {
                LOGGER.log(Level.SEVERE, "exception during eval()", ex);
            }
        });
    }
}
