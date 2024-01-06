import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test

class TestGlobalVarPassing {


    @Test
    fun test() {
        val code0 = """
            |println(G["a"])
            |println(G["b"])
            |G!!["a"] = 666
            |G!!["b"] = "xxx"
            |
        """.trimMargin()
        val code1 = """
            |println(G["a"])
            |println(G["b"])
            |
        """.trimMargin()

        val globalMap = GlobalMap()
        globalMap["a"] = 1
        globalMap["b"] = "abc"

        val s0 = Scripting(mode = ShareMode.SHARE_CONTEXT)
        val (r0, e0) = s0.runScript<Unit>(code0, mapOf("G" to globalMap))
        println("r0 = $r0, e0 = $e0")
        val s1 = Scripting(mode = ShareMode.SHARE_CONTEXT)
        val (r1, e1) = s1.runScript<Unit>(code1, mapOf("G" to globalMap))
        println("r1 = $r1, e1 = $e1")

        globalMap["a"] = 8888
        globalMap["b"] = "opq"

        val (r2, e2) = s1.runScript<Unit>(code1)

    }

}

class GlobalMap {
    private val m = mutableMapOf<String, Any?>()

    operator fun get(key: String): Any? = m[key]
    operator fun set(key: String, value: Any?) {
        m[key] = value
    }
}