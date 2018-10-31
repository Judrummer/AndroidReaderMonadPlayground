package com.judrummer.androidreadermonadplayground.delegate

import io.paperdb.Paper
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PaperDelegate<T>(private val defaultValue: T? = null,
                       private val key: String? = null) : ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
            defaultValue?.let { Paper.book().read(key ?: property.name, it) }
                    ?: Paper.book().read(key ?: property.name)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        Paper.book().write(key ?: property.name, value)
    }

}