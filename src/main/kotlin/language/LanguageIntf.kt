package com.isyscore.kotlin.scrpting.language

import com.isyscore.kotlin.scrpting.ScriptType
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

fun interface LanguageIntf {
    fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine
}

val languages = mapOf(
    ScriptType.KOTLIN to LangKotlin,
    ScriptType.JAVA to LangJava,
    ScriptType.JAVASCRIPT to LangJavaScript,
    ScriptType.PYTHON to LangPython,
    ScriptType.GROOVY to LangGroovy,
    ScriptType.RUBY to LangRuby,
)
