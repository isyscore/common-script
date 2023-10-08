import com.isyscore.kotlin.common.go
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import kotlinx.coroutines.async
import org.junit.Test

class TestShareContext {

    @Test
    fun test() {
        val code = """
            |println(a)
            |println(b)
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_CONTEXT)
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
            |sayHello("rarnu")
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_CONTEXT)
        val (ret, err) = kts.runScript<Unit>(code0)
        println("ret = $ret, err = $err")
        val (ret1, err1) = kts.runScript<String>(code1)
        println("ret1 = $ret1, err1 = $err1")
    }

    @Volatile var done = false

    @Test
    fun testCoroutine() {
        val code = """
            |println(a)
            |println(b)
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_CONTEXT)
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

    @Test
    fun testInvoke() {
        val code = """
            |fun sayHello(name: String): String = "Hello ${"$"}name"
            |
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_CONTEXT)
        kts.runScript<Unit>(code)
        val (ret, err) = kts.invoke<String>("sayHello", mapOf("name" to "rarnu"))
        println("ret = $ret, err = $err")
        val (ret1, err1) = kts.invoke<String>("sayHello", mapOf("name" to "isyscore"))
        println("ret = $ret1, err = $err1")
    }

    @Test
    fun testGetValue() {
        val code = """
            |val a = "rarnu"
            |val b = "isyscore"
            |
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SHARE_CONTEXT)
        kts.runScript<Unit>(code)
        val (ret, err) = kts.getValue<String>("a")
        println("ret = $ret, err = $err")
        val (ret1, err1) = kts.getValue<String>("b")
        println("ret = $ret1, err = $err1")
    }




}