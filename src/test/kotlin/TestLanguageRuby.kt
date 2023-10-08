import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test

class TestLanguageRuby {

    @Test
    fun test() {
        val code = """
            |puts "Input A: #{a}"
            |puts "Input B: #{b}"
            |op = a + b
            |op
        """.trimMargin()
        val ja = Scripting(type = ScriptType.RUBY, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Long>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
    }
}