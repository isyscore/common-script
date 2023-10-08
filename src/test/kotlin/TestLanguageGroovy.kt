import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test

class TestLanguageGroovy {

    @Test
    fun test() {
        val code = """
            |println("Input A: " + a);
            |println("Input B: " + b);
            |def output = a + b;
            |output
        """.trimMargin()
        val ja = Scripting(type = ScriptType.GROOVY, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Int>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
    }
}