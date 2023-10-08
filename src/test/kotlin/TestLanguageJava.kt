import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test

class TestLanguageJava {

    @Test
    fun test() {
        val code = """
            |System.out.println("Input A: " + a);
            |System.out.println("Input B: " + b);
            |int output = a + b;
            |output
        """.trimMargin()
        val ja = Scripting(type = ScriptType.JAVA, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Int>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
    }

}