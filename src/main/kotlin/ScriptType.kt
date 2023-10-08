package com.isyscore.kotlin.scrpting

enum class ScriptType {
    KOTLIN,
    JAVA,

    /**
     * JavaScript 使用 GraalVM 作为其实现，需要以 GraalVM JDK 作为运行时
     */
    JAVASCRIPT,

    GROOVY,
    PYTHON,
    RUBY,
    PASCAL,
}