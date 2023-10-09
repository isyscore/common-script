@file:Suppress("UNCHECKED_CAST")

package com.isyscore.kotlin.scrpting.extension

fun <T> Any.V(field: String): T? =
    this::class.java
        .getDeclaredField(field).apply {
            isAccessible = true
        }.get(this) as? T

fun <T> Any.I(methodName: String, params: Map<Class<*>, Any?>? = null): T? =
    this::class.java
        .getDeclaredMethod(methodName, *(params ?: mapOf()).map { it.key }.toTypedArray()).apply {
            isAccessible = true
        }.invoke(this, *(params ?: mapOf()).map { it.value }.toTypedArray()) as? T
