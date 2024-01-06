import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import org.junit.Test

class TestNumkt {

    @Test
    fun test() {
        val code = """
            |import com.rarnu.numkt.api.Numkt
            |import com.rarnu.numkt.api.arange
            |import com.rarnu.numkt.api.linalg.dot
            |import com.rarnu.numkt.ndarray.operations.map
            |import com.rarnu.numkt.ndarray.operations.times
            |typealias np = Numkt
            |
            |val a = np.arange<Int>(15).reshape(3, 5)
            |println(a)
            |val b = np.arange<Int>(15).map { it * it }.reshape(3, 5)
            |println(b)
            |val c = a * b
            |println(c)
            |val d = c.transpose().dot(a)
            |println(d)
        """.trimMargin()
        val kts = Scripting(mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = kts.runScript<Unit>(code)
        println("ret = $ret, err = $err")
    }

}