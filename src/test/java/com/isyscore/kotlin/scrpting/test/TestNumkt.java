package com.isyscore.kotlin.scrpting.test;

import com.isyscore.kotlin.scrpting.ScriptType;
import com.isyscore.kotlin.scrpting.Scripting;
import com.isyscore.kotlin.scrpting.ShareMode;
import kotlin.Pair;
import org.junit.Test;

public class TestNumkt {

    @Test
    public void test() {
        String code = "import com.rarnu.numkt.api.Numkt\n" +
                "import com.rarnu.numkt.api.arange\n" +
                "import com.rarnu.numkt.api.linalg.dot\n" +
                "import com.rarnu.numkt.ndarray.operations.map\n" +
                "import com.rarnu.numkt.ndarray.operations.times\n" +
                "typealias np = Numkt\n" +
                "\n" +
                "val a = np.arange<Int>(15).reshape(3, 5)\n" +
                "println(a)\n" +
                "val b = np.arange<Int>(15).map { it * it }.reshape(3, 5)\n" +
                "println(b)\n" +
                "val c = a * b\n" +
                "println(c)\n" +
                "val d = c.transpose().dot(a)\n" +
                "println(d)";
        Scripting ja = new Scripting(ScriptType.KOTLIN, ShareMode.SINGLE_CONTEXT);
        Pair<Object, Exception> ret = ja.runScript(code, null);
        System.out.println(String.format("ret = %s, err = %s", ret.getFirst(), ret.getSecond()));
    }

}
