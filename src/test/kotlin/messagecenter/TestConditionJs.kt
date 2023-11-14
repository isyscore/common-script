package messagecenter

import org.junit.Test

class TestConditionJs {

    @Test
    fun testCommon() {
        val code = """sender === 'master' && content.includes('aa')"""
        assert(testConditionJs(code))
        val code1 = """ sender === 'master' && content.includes('bbb') """
        assert(!testConditionJs(code1))
    }

    @Test
    fun testLambda() {
        val code = """
            |let count = 0;
            |for (let i = 0; i < content.length; i++) {
            |   if (content[i] === 'c') count++;
            |}
            |count > 4;
        """.trimMargin()
        assert(testConditionJs(code))
        val code1 = """
            |let count = 0;
            |for (let i = 0; i < content.length; i++) {
            |   if (content[i] === 'c') count++;
            |}
            |count > 8;
        """.trimMargin()
        assert(!testConditionJs(code1))
    }

}