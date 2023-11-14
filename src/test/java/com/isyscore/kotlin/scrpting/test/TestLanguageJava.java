package com.isyscore.kotlin.scrpting.test;

import com.isyscore.kotlin.scrpting.ScriptType;
import com.isyscore.kotlin.scrpting.Scripting;
import com.isyscore.kotlin.scrpting.ShareMode;
import com.isyscore.kotlin.scrpting.extension.UnknownExtensionKt;
import kotlin.Pair;
import org.junit.Test;

public class TestLanguageJava {

    @Test
    public void test() {

        String code = "class SystemInfo {\n" +
                "    private String osName;\n" +
                "    private String osArch;\n" +
                "    public SystemInfo(String on, String oa) {\n" +
                "        osName = on;\n" +
                "        osArch = oa;\n" +
                "    }\n" +
                "    public String getOsName() {\n" +
                "        return osName;\n" +
                "    }\n" +
                "    public String getOsArch() {\n" +
                "        return osArch;\n" +
                "    }\n" +
                "    public String print() {\n" +
                "        String str = String.format(\"osName = %s, osArch = %s\", osName, osArch);\n" +
                "        System.out.println(str);\n" +
                "        return str;\n" +
                "    }\n" +
                "}\n" +
                "SystemInfo si = new SystemInfo(System.getProperty(\"os.name\"), System.getProperty(\"os.arch\"));\n" +
                "System.out.println(si);\n" +
                "si // return it";

        Scripting ja = new Scripting(ScriptType.JAVA, ShareMode.SINGLE_CONTEXT);
        Pair<Object, Exception> ret = ja.runScript(code, null);
        System.out.println(String.format("ret = %s, err = %s", ret.getFirst(), ret.getSecond()));
        System.out.println(String.format("osName = %s", UnknownExtensionKt.<String>V(ret.getFirst(), "osName")));
        System.out.println(String.format("osArch = %s", UnknownExtensionKt.<String>V(ret.getFirst(), "osArch")));
        System.out.println(String.format("invoke = %s", UnknownExtensionKt.<String>I(ret.getFirst(), "print", null)));
    }

}
