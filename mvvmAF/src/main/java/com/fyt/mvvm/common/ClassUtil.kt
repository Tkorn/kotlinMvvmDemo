package com.fyt.mvvm.common

import java.lang.reflect.ParameterizedType

class ClassUtil {
    companion object {
        inline fun <reified T> getClassType(K: Any, position:Int):T {
            return (K.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[position] as T
        }
    }
}
