package com.fyt.mvvm.globalsetting

/*
* 全局的请求异常处理
* 具体实现交给继承者 通过 koin 注入实例
* */
interface IResponseErrorListener{

    fun handleResponseError(t: Throwable)
}