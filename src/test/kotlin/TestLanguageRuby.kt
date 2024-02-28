import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import com.isyscore.kotlin.scrpting.extension.getField
import org.jruby.RubyObject
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

    @Test
    fun testReturnObject() {
        val code = """
            |class MyClass
            |    @name = "rarnu"
            |    @age = 40
            |    def print1
            |        puts @name
            |    end
            |    def print2
            |       puts @age
            |    end
            |end
            |myobj = MyClass.new
            |myobj
        """.trimMargin()
        val ja = Scripting(type = ScriptType.RUBY, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<RubyObject>(code)
        println("ret = $ret, err = $err")
        println("name = ${ret?.getField("@name")}")
    }
}