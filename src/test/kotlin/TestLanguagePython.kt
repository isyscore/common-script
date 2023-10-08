import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test

class TestLanguagePython {

    @Test
    fun test() {
        val code = """
            |print("Input A: %d" % a)
            |print("Input B: %d" % b)
            |op = a + b
        """.trimMargin()
        val ja = Scripting(type = ScriptType.PYTHON, mode = ShareMode.SHARE_CONTEXT)
        val (ret, err) = ja.runScript<Int>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
        val (ret1, err1) = ja.getValue<Int>("op")
        println("ret = $ret1, err = $err1")
    }

}