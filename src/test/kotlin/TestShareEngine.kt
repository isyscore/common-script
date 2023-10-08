import com.isyscore.kotlin.common.go
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import kotlinx.coroutines.async
import org.junit.Test

class TestShareEngine {

    @Test
    fun test() {
        val code = """
            |println(a)
            |println(b)
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_ENGINE)
        val (ret, err) = kts.runScript<Unit>(code, mapOf("a" to "xxx", "b" to "yyy"))
        println("ret = $ret, err = $err")
        val (ret1, err1) = kts.runScript<Unit>(code, mapOf("a" to "aaa", "b" to "bbb"))
        println("ret = $ret1, err = $err1")
    }

    @Test
    fun testMultiEval() {
        val code0 = """
            |fun sayHello(name: String): String = "hello ${"$"}name"
            |
        """.trimMargin()
        val code1 = """
            |println(sayHello("rarnu"))
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_ENGINE)
        val (ret, err) = kts.runScript<Unit>(code0)
        println("ret = $ret, err = $err")
        val (ret1, err1) = kts.runScript<Unit>(code1)
        println("ret1 = $ret1, err1 = $err1")
    }

    @Volatile var done = false

    @Test
    fun testCoroutine() {
        val code = """
            |println(a)
            |println(b)
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_ENGINE)
        go {
            val job1 = async {
                val (ret, err) = kts.runScript<Unit>(code, mapOf("a" to "xxx", "b" to "yyy"))
                println("ret = $ret, err = $err")
            }
            val job2 = async {
                val (ret, err) = kts.runScript<Unit>(code, mapOf("a" to "aaa", "b" to "bbb"))
                println("ret = $ret, err = $err")
            }
            job1.await()
            job2.await()
            done = true
        }
        while (!done) { }
    }

}