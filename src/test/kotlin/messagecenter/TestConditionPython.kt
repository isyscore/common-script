package messagecenter

import org.junit.Test

class TestConditionPython {

    @Test
    fun testCommon() {
        val code = """ret = sender == 'master' and content.__contains__('aa')"""
        assert(testConditionPython(code))
        val code1 = """ret = sender == 'master' and content.__contains__('bbb')"""
        assert(!testConditionPython(code1))
    }

    @Test
    fun testLambda() {
        val code = """
            |count = 0
            |for c in content:
            |   if c == 'c':
            |       count += 1
            |ret = count > 4""".trimMargin()
        assert(testConditionPython(code))
        val code1 = """
            |count = 0
            |for c in content:
            |   if c == 'c':
            |       count += 1
            |ret = count > 8""".trimMargin()
        assert(!testConditionPython(code1))
    }

}