package com.isyscore.kotlin.scrpting.language

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine
import org.graalvm.polyglot.Context
import org.graalvm.polyglot.EnvironmentAccess
import org.graalvm.polyglot.io.IOAccess
import java.util.logging.Handler
import java.util.logging.LogRecord
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangJavaScript : LanguageIntf {

    init {
        System.setProperty("engine.WarnInterpreterOnly", "false")
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false")
        System.setProperty("js.nashorn-compat", "true")
    }

    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine = try {
        val ctx = Context.newBuilder("js")
            .allowAllAccess(true)
            .allowCreateThread(true)
            .allowExperimentalOptions(true)
            .allowHostClassLookup { true }
            .allowIO(IOAccess.ALL)
            .allowNativeAccess(true)
            .allowCreateProcess(true)
            .allowEnvironmentAccess(EnvironmentAccess.INHERIT)
            .option("js.ecmascript-version", "2023")
            .option("js.esm-eval-returns-exports", "true")
            .logHandler(object : Handler() {
                override fun publish(record: LogRecord?) {
                    println(record?.message)
                }

                override fun flush() {}
                override fun close() {}
            })
        GraalJSScriptEngine.create(null, ctx)
    } catch (th: Throwable) {
        throw RuntimeException(th)
    }

}