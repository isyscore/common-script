package com.isyscore.kotlin.scrpting.language

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.script.experimental.jvmhost.jsr223.KotlinJsr223ScriptEngineImpl

object LangKotlin: LanguageIntf {

    // Compilable
    override fun getScriptEngine(mgr: ScriptEngineManager): ScriptEngine =
        mgr.getEngineByExtension("kts") as KotlinJsr223ScriptEngineImpl
}