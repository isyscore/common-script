package com.isyscore.kotlin.scrpting.language

import org.python.jsr223.PyScriptEngine
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangPython : LanguageIntf {
    init {
        try {
            Class.forName("org.python.core.Options").getDeclaredField("importSite").set(null, false)
        } catch (_: Throwable) {

        }
    }

    // Compilable
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByName("python") as PyScriptEngine
}