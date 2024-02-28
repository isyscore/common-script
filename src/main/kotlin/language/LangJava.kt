package com.isyscore.kotlin.scrpting.language

import ch.obermuhlner.scriptengine.jshell.JShellScriptEngine
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object LangJava: LanguageIntf {

    // Compilable
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByExtension("jshell") as JShellScriptEngine

}