package inc

import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import org.junit.Test

class TestJsImport {

    @Test
    fun test() {
        val code = """
            |var common = com.isyscore.kotlin.common
            |// var Quadruple = com.isyscore.kotlin.common.Quadruple
            |
            |let o = new common.Quadruple(1,2,3,4)
            |console.log(o)
        """.trimMargin()
        val script = Scripting(type = ScriptType.JAVASCRIPT)
        val (ret, err) = script.runScript<Unit>(code)
        println("ret = $ret, err = $err")

    }

}