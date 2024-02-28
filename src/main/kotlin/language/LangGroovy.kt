package com.isyscore.kotlin.scrpting.language

import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangGroovy : LanguageIntf {

    // Compilable
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByName("groovy") as GroovyScriptEngineImpl
}