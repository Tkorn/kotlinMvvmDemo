package com.fyt.myapplication

import android.content.Context
import com.fyt.mvvm.globalsetting.IGlobalConfiguration
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class GlobalConfigurationImpl @Inject constructor(@ApplicationContext val content: Context) :IGlobalConfiguration{
    override fun configBaseUrl(): String = "https://www.wanandroid.com/"

    override fun configGson(builder: GsonBuilder) {

    }

    override fun configOkHttpClient(builder: OkHttpClient.Builder) {
        builder
            .writeTimeout(3000, TimeUnit.MILLISECONDS)
            .connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(3000, TimeUnit.MILLISECONDS)
    }

    override fun configRetrofit(builder: Retrofit.Builder) {
    }
}