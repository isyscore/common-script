import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.extension.I
import com.isyscore.kotlin.scrpting.extension.V
import org.junit.Test

class TestReturnObject {

    @Test
    fun test() {
        val code = """
            |data class SystemInfo(val osName: String, val osArch: String) {
            |    fun print(): String {
            |        val str = "osName = ${"$"}{osName}, osArch = ${"$"}{osArch}"
            |        println(str)
            |        return str
            |    }
            |}
            |val si = SystemInfo(System.getProperty("os.name"), System.getProperty("os.arch"))
            |println("inScript = ${"$"}{si}")
            |si // return it
        """.trimMargin()
        val kts = Scripting()
        val (ret, err) = kts.runScript<Any>(code)
        println("ret = $ret, err = $err")
        println("osName = ${ret?.V<String>("osName")}")
        println("osName = ${ret?.V<String>("osArch")}")
        println("invoke = ${ret?.I<String>("print")}")
    }

}