package inc

import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import org.junit.Test

class TestPythonImport {

    @Test
    fun test() {
        val code = """
            |# import com.isyscore.kotlin.common
            |from com.isyscore.kotlin.common import *
            |
            |o = Quadruple(1,2,3,4)
            |print(o)
            |
            |print("hello")
        """.trimMargin()
        val script = Scripting(type = ScriptType.PYTHON)
        val (ret, err) = script.runScript<Unit>(code)
        println("ret = $ret, err = $err")
    }

}