package com.fyt.myapplication.mvvm.repository.bean

sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}