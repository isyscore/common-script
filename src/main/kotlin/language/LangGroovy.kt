package com.isyscore.kotlin.scrpting.language

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangGroovy : LanguageIntf {
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByName("groovy")
}