package com.isyscore.kotlin.scrpting.language

import org.jruby.embed.jsr223.JRubyEngine
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangRuby: LanguageIntf {

    // Compilable
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByName("jruby") as JRubyEngine
}