import org.junit.Test
import java.util.*
import javax.script.CompiledScript
import javax.script.ScriptContext
import javax.script.ScriptEngineManager
import kotlin.script.experimental.jvmhost.jsr223.KotlinJsr223ScriptEngineImpl

class TestKotlinScriptCompile {

    class Task(val str: String): TimerTask() {

        val code = """
            |import java.util.*
            |
            |val list = listOf(1, 2, 3)
            |println(list)
            |println(str)
            """.trimMargin()
        val script = ScriptEngineManager().getEngineByExtension("kts") as KotlinJsr223ScriptEngineImpl
        val cr: CompiledScript

        init {
            val bindings = script.createBindings()
            bindings["str"] = str
            script.setBindings(bindings, ScriptContext.ENGINE_SCOPE)
            cr = script.compile(code)
        }

        override fun run() {
            try {
                cr.eval()
            } catch (e: Exception) {
                println("error: $e")
            }
        }

    }


    @Test
    fun test() {

        val tmr = Timer()
        val task = Task("rarnu")
        tmr.schedule(task, 0, 5000L)

        while (true) {

        }

    }

}