package com.isyscore.kotlin.scrpting.language

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangJava: LanguageIntf {
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByExtension("jshell")

}