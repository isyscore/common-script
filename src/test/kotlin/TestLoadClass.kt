import com.isyscore.kotlin.scrpting.Scripting
import org.junit.Test
import java.io.File
import java.net.URL
import java.net.URLClassLoader

class TestLoadClass {

    fun addToClassPath(jar: File) {
        val cl = ClassLoader.getSystemClassLoader()
        val m = URLClassLoader::class.java.getDeclaredMethod("addURL", URL::class.java)
        m.isAccessible = true
        m.invoke(cl, jar.toURI().toURL())
    }

    @Test
    fun test() {
        val jar = File("license.jar")
        addToClassPath(jar)
//        val cl = URLClassLoader(arrayOf(jar.toURI().toURL()), this.javaClass.classLoader)
//        val v = Class.forName("com.isyscore.os.sdk.license.entity.LicenseVO")
//        println(v)

        val code = """
            |import com.isyscore.os.sdk.license.entity.LicenseVO
            |
            |val lic = LicenseVO()
            |println(lic)
        """.trimMargin()
        val scripting = Scripting()
        val (_, err) = scripting.runScript<Unit>(code)
        println(err)

    }
}