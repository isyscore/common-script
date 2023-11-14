package messagecenter

import org.junit.Test

class TestConditionJava {

    @Test
    fun testCommon() {
        val code = """ sender.equals("master") && content.contains("aa") """
        assert(testConditionJava(code))
        val code1 = """ sender.equals("master") && content.contains("bbb") """
        assert(!testConditionJava(code1))
    }

    @Test
    fun testLambda() {
        val code = """
            |int count = 0;
            |for (char c: content.toCharArray()) {
            |   if (c == 'c') count++;
            |}
            |count > 4;
        """.trimMargin()
        assert(testConditionJava(code))
        val code1 = """
            |int count = 0;
            |for (char c: content.toCharArray()) {
            |   if (c == 'c') count++;
            |}
            |count > 8;
        """.trimMargin()
        assert(!testConditionJava(code1))
    }

}