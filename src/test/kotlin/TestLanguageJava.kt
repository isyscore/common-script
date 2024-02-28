import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import com.isyscore.kotlin.scrpting.extension.I
import com.isyscore.kotlin.scrpting.extension.V
import org.junit.Test

class TestLanguageJava {

    @Test
    fun test() {
        val code = """
            |System.out.println("Input A: " + a);
            |System.out.println("Input B: " + b);
            |int output = a + b;
            |output
        """.trimMargin()
        val ja = Scripting(type = ScriptType.JAVA, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Int>(code, mapOf("a" to 1, "b" to 2))
        println("ret = $ret, err = $err")
    }

    @Test
    fun testReturnObject() {
        val code = """
            |class SystemInfo {
            |    private String osName;
            |    private String osArch;
            |    public SystemInfo(String on, String oa) {
            |        osName = on;
            |        osArch = oa;
            |    }
            |    public String getOsName() {
            |        return osName;
            |    }
            |    public String getOsArch() {
            |        return osArch;
            |    }
            |    public String print() {
            |        String str = String.format("osName = %s, osArch = %s", osName, osArch);
            |        System.out.println(str);
            |        return str;
            |    }
            |}
            |SystemInfo si = new SystemInfo(System.getProperty("os.name"), System.getProperty("os.arch"));
            |System.out.println(si);
            |si // return it
        """.trimMargin()
        val ja = Scripting(type = ScriptType.JAVA, mode = ShareMode.SINGLE_CONTEXT)
        val (ret, err) = ja.runScript<Any>(code)
        println("ret = $ret, err = $err")
        println("osName = ${ret?.V<String>("osName")}")
        println("osName = ${ret?.V<String>("osArch")}")
        println("invoke = ${ret?.I<String>("print")}")
    }



}