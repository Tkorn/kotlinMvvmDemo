package com.fyt.myapplication.base
data class BaseResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)