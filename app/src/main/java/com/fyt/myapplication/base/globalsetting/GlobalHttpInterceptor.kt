package com.fyt.myapplication.base.globalsetting

import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Exception

class GlobalHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        }catch (e: Exception){
            throw e
        }
    }
}