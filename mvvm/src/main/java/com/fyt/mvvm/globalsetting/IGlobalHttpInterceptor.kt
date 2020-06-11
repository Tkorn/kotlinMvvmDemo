package com.fyt.mvvm.globalsetting

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

interface IGlobalHttpInterceptor {

    fun onHttpResultResponse(chain: Interceptor.Chain, response: Response): Response

    fun onHttpRequestBefore(chain: Interceptor.Chain, request: Request): Request


}