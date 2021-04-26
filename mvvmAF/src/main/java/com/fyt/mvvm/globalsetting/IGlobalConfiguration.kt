package com.fyt.mvvm.globalsetting

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface IGlobalConfiguration{

    fun configBaseUrl():String

    fun configGson(builder: GsonBuilder)

    fun configOkHttpClient(builder: OkHttpClient.Builder)

    fun configRetrofit(builder: Retrofit.Builder);
}