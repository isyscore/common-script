package messagecenter

import com.isyscore.kotlin.scrpting.ScriptType
import com.isyscore.kotlin.scrpting.Scripting
import com.isyscore.kotlin.scrpting.ShareMode
import java.text.SimpleDateFormat
import java.util.Date

/**
 * 需要注入的变量
 */
val injData = mapOf(
    "subject" to "消息标题",
    "content" to "消息内容aaaaacdcdcdcdcd",
    "level" to 1, // 1-通常，2-紧急
    "sender" to "master",
    "receiver" to listOf("user1", "user2", "user3"),
    "sendDate" to SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date()),
    "timestamp" to System.currentTimeMillis()
)

fun testConditionKotlin(code: String): Boolean {
    val kts = Scripting(type = ScriptType.KOTLIN)
    val (ret, err) = kts.runScript<Boolean>(code, injData)
    println("run condition: $ret")
    if (err != null) {
        println("testCondition error: $err")
    }
    return ret!!
}

fun testConditionJava(code: String): Boolean {
    val kts = Scripting(type = ScriptType.JAVA)
    val (ret, err) = kts.runScript<Boolean>(code, injData)
    println("run condition: $ret")
    if (err != null) {
        println("testCondition error: $err")
    }
    return ret!!
}

fun testConditionJs(code: String): Boolean {
    val kts = Scripting(type = ScriptType.JAVASCRIPT)
    val (ret, err) = kts.runScript<Boolean>(code, injData)
    println("run condition: $ret")
    if (err != null) {
        println("testCondition error: $err")
    }
    return ret!!
}

fun testConditionPython(code: String): Boolean {
    val kts = Scripting(type = ScriptType.PYTHON, mode = ShareMode.SHARE_CONTEXT)
    kts.runScript<Unit>(code, injData)
    val (ret, err) = kts.getValue<Boolean>("ret")
    println("run condition: $ret")
    if (err != null) {
        println("testCondition error: $err")
    }
    return ret!!
}