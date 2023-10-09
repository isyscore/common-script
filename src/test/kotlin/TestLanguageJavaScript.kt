import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import com.isyscore.kotlin.scrpting.extension.V
import com.oracle.truffle.polyglot.PolyglotMapEntryAndFunction
import org.junit.Test

class TestLanguageJavaScript {

    @Test
    fun test() {
        val code = """
            |console.log('Input A: ' + a)
            |console.log('Input B: ' + b)
            |let output = a + b
            |output
        """.trimMargin()
        val ja = Scripting(type = ScriptType.JAVASCRIPT, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Int>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
    }

    @Test
    fun testReturnObject() {
        val code = """
            |let obj = {a: "xxx", b: "yyy"}
            |obj
        """.trimMargin()
        val ja = Scripting(type = ScriptType.JAVASCRIPT, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Map<String, Any?>>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
        println("a = ${ret!!["a"]}")
    }

}