package com.fyt.mvvm.globalsetting

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
/*
* 处理 Http 请求和响应结果的处理类
* 具体实现交给继承者，通过koin 注入实例
* */
interface IGlobalHttpInterceptor {

    fun onHttpResultResponse(chain: Interceptor.Chain, response: Response): Response

    fun onHttpRequestBefore(chain: Interceptor.Chain, request: Request): Request


}