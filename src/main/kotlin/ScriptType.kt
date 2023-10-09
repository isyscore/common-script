package com.isyscore.kotlin.scrpting

enum class ScriptType {
    /**
     * 使用 Kotlin 来编写脚本
     */
    KOTLIN,

    /**
     * 使用 Java 来编写脚本
     */
    JAVA,

    /**
     * JavaScript 使用 GraalJS 作为其实现，建议以 GraalVM JDK 作为运行时，OpenJDK 对 GraalJS 的性能方面兼容极差
     */
    JAVASCRIPT,

    /**
     * 使用 Groovy 来编写脚本
     */
    GROOVY,

    /**
     * 使用 Python 来编写脚本，使用 Jython 作为其实现
     */
    PYTHON,

    /**
     * 使用 Ruby 来编写脚本，使用 JRuby 作为其实现
     */
    RUBY,
}