package com.isyscore.kotlin.scrpting.extension

import org.python.core.PyInstance
import org.python.core.PyObject
import org.python.core.PyStringMap

fun PyInstance.getField(fieldName: String): PyObject? =
    (instclass?.__dict__ as PyStringMap).map[fieldName]
