package com.fyt.myapplication.mvvm.repository.bean

data class BaseResponse <T>(val data:T,val errorCode:Int,val errorMsg:String)