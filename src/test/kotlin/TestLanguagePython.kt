import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import com.isyscore.kotlin.scrpting.extension.getField
import org.junit.Test
import org.python.core.PyInstance
import org.python.core.PyString
import org.python.core.PyStringMap

class TestLanguagePython {

    @Test
    fun test() {
        val code = """
            |print("Input A: %d" % a)
            |print("Input B: %d" % b)
            |op = a + b
        """.trimMargin()
        val ja = Scripting(type = ScriptType.PYTHON, mode = ShareMode.SHARE_CONTEXT)
        val (ret, err) = ja.runScript<Unit>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
        val (ret1, err1) = ja.getValue<Int>("op")
        println("ret = $ret1, err = $err1")
    }

    @Test
    fun testReturnObject() {
        val code = """
            |class myclass:
            |   name = "rarnu"
            |   age = 40
            |myobj = myclass()
        """.trimMargin()
        val ja = Scripting(type = ScriptType.PYTHON, mode = ShareMode.SHARE_CONTEXT)
        val (ret, err) = ja.runScript<Unit>(code)
        println("ret = $ret, err = $err")
        val (ret1, err1) = ja.getValue<PyInstance>("myobj")
        println("ret = $ret1, err = $err1")
        println(ret1?.getField("name"))
        println(ret1?.getField("age"))
    }

}