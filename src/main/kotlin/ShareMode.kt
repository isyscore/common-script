package com.isyscore.kotlin.scrpting

enum class ShareMode {
    /**
     * 每次启动脚本时都使用全新脚本引擎实例
     */
    SINGLE_CONTEXT,

    /**
     * 启动时复用脚本引擎，并且使用不同的上下文
     */
    SHARE_ENGINE,

    /**
     * 启动时复用脚本引擎和相关上下文，可用作连续脚本调用
     */
    SHARE_CONTEXT,
}