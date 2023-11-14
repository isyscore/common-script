package messagecenter

import org.junit.Test

class TestConditionKotlin {

    @Test
    fun testCommon() {
        val code = """ sender == "master" && content.contains("aa") """
        assert(testConditionKotlin(code))
        val code1 = """ sender == "master" && content.contains("bbb") """
        assert(!testConditionKotlin(code1))
    }

    @Test
    fun testLambda() {
        val code = """ content.count { it == 'c' } > 4 """
        assert(testConditionKotlin(code))
        val code1 = """ content.count { it == 'c' } > 8 """
        assert(!testConditionKotlin(code1))
    }

}