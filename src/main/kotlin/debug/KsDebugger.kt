package com.isyscore.kotlin.scrpting.debug

import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleScriptContext
import kotlin.concurrent.thread
import kotlin.script.experimental.jvmhost.jsr223.KotlinJsr223ScriptEngineImpl

/**
 * Kotlin 脚本调试器
 */
class KsDebugger(
    /**
     * 要调试的代码
     */
    val code: String,
    /**
     * 要注入的参数
     */
    val params: Map<String, Any?> = mapOf(),
    /**
     * 断点列表，指示的是行数
     */
    val breakpoints: List<Int> = listOf(),
    /**
     * 监视变量名
     */
    val watchNames: List<String> = listOf()
) {

    private val realBreakpoints = breakpoints.toMutableList()

    @Volatile
    private var err: Exception? = null

    @Volatile
    private var currentLine = -1

    @Volatile
    private var currentRepl = ""

    @Volatile
    private var currentWatchList: Map<String, Any?> = mapOf()

    @Volatile
    private var isCompleted = true

    @Volatile
    private var paused = false

    /**
     * 中断时的回调
     */
    private val onInterrupt: (ScriptEngine, ScriptContext, Int, String) -> Unit = { eng, ctx, line, repl ->
        paused = true
        currentLine = line
        currentRepl = repl
        // 如果暂时状态，就不往下进行了，直接接收到命令
        currentWatchList = watchNames.associateWith { n -> eng.eval(n, ctx) }
        while (paused && !isCompleted) {
        }
    }

    private val engine = ScriptEngineManager().getEngineByExtension("kts") as KotlinJsr223ScriptEngineImpl
    private val ctx = SimpleScriptContext().apply {
        setBindings(engine.createBindings().apply {
            params.forEach { (k, v) -> this[k] = v }
        }, ScriptContext.ENGINE_SCOPE)
    }

    /**
     * 开始调试
     */
    fun start() = thread {
        isCompleted = false
        val codeLines = code.split("\n")
        var stashCode: String = ""
        var inLambda = false
        for (i in codeLines.indices) {
            if (codeLines[i].trim().isEmpty()) continue
            val line = codeLines[i] + "\n"
            stashCode += line
            try {
                if (realBreakpoints.contains(i)) {
                    if (!inLambda) {
                        onInterrupt(engine, ctx, i, stashCode)
                    } else {
                        realBreakpoints.add(i + 1)
                    }
                }
                engine.eval(stashCode, ctx)
                stashCode = ""
                inLambda = false
            } catch (e: Exception) {
                if ("$e".contains("Error: incomplete code.")) {
                    inLambda = true
                } else {
                    err = e
                    break
                }
            }
            if (isCompleted) break
        }
        isCompleted = true
    }

    /**
     * 前进一行
     */
    fun nextLine() {
        realBreakpoints.add(currentLine + 1)
        paused = false
    }

    /**
     * 前进到下一个断点
     */
    fun nextBreakpoint() {
        paused = false
    }

    /**
     * 运行到结束
     */
    fun runToEnd() {
        realBreakpoints.clear()
        paused = false
    }

    /**
     * 强制结束
     */
    fun stop() {
        realBreakpoints.clear()
        paused = false
        isCompleted = true
    }

    /**
     * 获取调试过程中的对象
     */
    fun getVar(name: String): Any? = currentWatchList[name]

    fun getAll(): Map<String, Any?> = currentWatchList

    fun currentLine(): Int = currentLine

    fun currentRepl(): String = currentRepl

    /**
     * 获得最后的异常信息
     */
    fun lastError(): Exception? = err

}