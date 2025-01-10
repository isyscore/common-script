import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test



class TestLanguageKotlin {

    @Test
    fun test() {
        val code = """
            |var outA: Int = a
            |var outB: Int = b
            |println("Input A: " + outA)
            |println("Input B: " + outB)
            |outA = 666
            |outB = 777
            |object {
            |    val a: Int = outA
            |    val b: Int = outB
            |}
        """.trimMargin()
        val ja = Scripting(type = ScriptType.KOTLIN, mode = ShareMode.SHARE_CONTEXT)
        val (succ, cr, err) = ja.compileScript(code, mapOf("a" to 0, "b" to 0))
        if (succ) {
            cr.eval()
        }
        val (ret, err) = ja.runScript<Any?>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")

    }

}

