package com.isyscore.kotlin.scrpting.extension

import org.jruby.RubyObject

fun RubyObject.getField(fieldName: String): RubyObject? =
    this.metaClass.variableList.firstOrNull { it.name == fieldName }?.value as? RubyObject