package com.fyt.mvvm.base

sealed class BaseResult<out T> {

    data class Success<out T>(val data: T) : BaseResult<T>()
    data class Error(val error: Throwable) : BaseResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}