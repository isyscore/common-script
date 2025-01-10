@file:Suppress("UNCHECKED_CAST")

package kdebug

import com.isyscore.kotlin.scrpting.debug.KsDebugger
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleScriptContext
import kotlin.script.experimental.jvmhost.jsr223.KotlinJsr223ScriptEngineImpl

class TestKotlinDebug {

    @Test
    fun test() {
        val code = """
            |val l1 = listOf(1, 2, 3, 4, 5, 6)
            |val l2 = l1.filter {
            |   it%2 == 0
            |}
            |println(l1)
            |println(l2)
        """.trimMargin()
        val kd = KsDebugger(code, breakpoints = mutableListOf(1, 5), watchNames = listOf("l1"))
        kd.start()
        Thread.sleep(2000L)
        println(kd.currentLine())
        println(kd.currentRepl())
        Thread.sleep(2000L)
        kd.nextLine()
        Thread.sleep(2000L)
        println(kd.currentLine())
        println(kd.currentRepl())

        kd.nextBreakpoint()
        Thread.sleep(2000L)
        println(kd.currentLine())
        println(kd.currentRepl())

        println(kd.getVar("l1"))

        kd.stop()

        Thread.sleep(2000L)

    }
}