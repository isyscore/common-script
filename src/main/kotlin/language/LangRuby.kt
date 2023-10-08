package com.isyscore.kotlin.scrpting.language

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangRuby: LanguageIntf {
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByName("jruby")
}