@file:Suppress("UNCHECKED_CAST")

package com.isyscore.kotlin.scrpting

import com.isyscore.kotlin.scrpting.ShareMode.*
import com.isyscore.kotlin.scrpting.language.LangKotlin
import com.isyscore.kotlin.scrpting.language.languages
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleScriptContext

class Scripting(
    /**
     * 脚本语言的类型，默认为
     * @see ScriptType.KOTLIN
     */
    private val type: ScriptType = ScriptType.KOTLIN,
    /**
     * 脚本的执行模式，在一次脚本执行中不能变更执行模式，默认模式为
     * @see ShareMode.SINGLE_CONTEXT
     */
    private val mode: ShareMode = SINGLE_CONTEXT
) {

    private var mgr: ScriptEngineManager = ScriptEngineManager()
    private val language = languages[type] ?: LangKotlin
    private lateinit var engine: ScriptEngine

    /**
     * 执行脚本代码
     * @param code 要执行的脚本代码
     * @param params 需要被送入脚本上下文的参数
     * @return 执行的返回类型实例，异常信息，执行正常时，异常信息为 null。
     *
     * 对于返回内容，JavaScript 返回对象时，返回类型为 Map<String, Any?> ，Python 返回对象时，返回类型为 PyInstance，Ruby 返回对象时，返回类型为 RubyObject，
     * 其他情况若是返回未知类型，均可使用 Any 作为返回类型，无返回的情况可以用 Unit 或 Any 来表示返回类型
     */
    fun <T> runScript(code: String, params: Map<String, Any?>? = null): Pair<T?, Exception?> {
        val e = when (mode) {
            SINGLE_CONTEXT -> {
                language.getScriptEngine(mgr)
            }

            else -> {
                if (!this::engine.isInitialized) {
                    engine = language.getScriptEngine(mgr)
                }
                engine
            }
        }
        putParameters(e, params)
        return try {
            e.eval(code) as? T to null
        } catch (ex: Exception) {
            null to ex
        }
    }

    private fun putParameters(eng: ScriptEngine, params: Map<String, Any?>? = null) {
        when (mode) {
            SINGLE_CONTEXT -> {
                if (params != null) {
                    for ((k, v) in params) {
                        eng.put(k, v)
                    }
                }
            }

            SHARE_ENGINE -> {
                val bindings = eng.createBindings()
                if (params != null) {
                    for ((k, v) in params) {
                        bindings[k] = v
                    }
                }
                // 生成全新的上下文
                val ctx = SimpleScriptContext()
                ctx.setBindings(bindings, ScriptContext.ENGINE_SCOPE)
                eng.context = ctx
            }

            SHARE_CONTEXT -> {
                var bindings = eng.getBindings(ScriptContext.ENGINE_SCOPE)
                if (bindings == null) {
                    bindings = eng.createBindings()
                }
                if (params != null) {
                    for ((k, v) in params) {
                        bindings[k] = v
                    }
                }
                eng.setBindings(bindings, ScriptContext.ENGINE_SCOPE)
            }
        }
    }

    /**
     * 设置脚本运行时所需参数，仅在 mode 为 SHARE_CONTEXT 时可以使用
     * @param params 需要被送入脚本上下文的参数
     */
    @Throws(IllegalArgumentException::class)
    fun setParams(params: Map<String, Any?>? = null) {
        if (mode != SHARE_CONTEXT) {
            throw IllegalArgumentException("mode must be SHARE_CONTEXT")
        }
        if (!this::engine.isInitialized) {
            engine = language.getScriptEngine(mgr)
        }
        putParameters(engine, params)
    }

    /**
     * 调用脚本内的函数，并获得其返回值
     * @param methodName 要调用的函数名称
     * @param params 调用的参数，以名值对的形式传入
     * @return 调用返回的值，在 Python 脚本的情况下，对象的返回类型为 PyInstance，在 JavaScript 脚本的情况下，对象的返回类型为 Map<String, Any?>，Ruby 返回对象时，返回类型为 RubyObject
     */
    @Throws(IllegalArgumentException::class)
    fun <T> invoke(methodName: String, params: Map<String, Any?>? = null): Pair<T?, Exception?> {
        if (mode != SHARE_CONTEXT) {
            throw IllegalArgumentException("mode must be SHARE_CONTEXT")
        }
        if (!this::engine.isInitialized) {
            engine = language.getScriptEngine(mgr)
        }
        putParameters(engine, params)
        var code = "$methodName("
        if (params != null) {
            code += params.map { it.key }.joinToString(",", prefix = "(", postfix = ")") { it }
        }
        code += ")"
        return try {
            engine.eval(code) as? T to null
        } catch (ex: Exception) {
            null to ex
        }
    }

    /**
     * 获取脚本上下文内的值
     * @param fieldName 对象/变量的名称
     * @return 对应的值，在 Python 脚本的情况下，对象的返回类型为 PyInstance，在 JavaScript 脚本的情况下，对象的返回类型为 Map<String, Any?>，Ruby 返回对象时，返回类型为 RubyObject
     */
    @Throws(IllegalArgumentException::class)
    fun <T> getValue(fieldName: String): Pair<T?, Exception?> {
        if (mode != SHARE_CONTEXT) {
            throw IllegalArgumentException("mode must be SHARE_CONTEXT")
        }
        if (!this::engine.isInitialized) {
            engine = language.getScriptEngine(mgr)
        }
        return try {
            if (type == ScriptType.PYTHON) {
                // python 脚本有特殊的获取方法
                engine.get(fieldName) as? T to null
            } else {
                engine.eval(fieldName) as? T to null
            }
        } catch (ex: Exception) {
            null to ex
        }
    }

}