package com.isyscore.kotlin.scrpting.language

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangPython : LanguageIntf {
    init {
        try {
            Class.forName("org.python.core.Options").getDeclaredField("importSite").set(null, false)
        } catch (_: Throwable) {

        }
    }

    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByName("python")
}