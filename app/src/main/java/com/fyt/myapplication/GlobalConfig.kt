package com.fyt.myapplication

import android.app.Application
import com.fyt.mvvm.globalsetting.IGlobalConfig
import okhttp3.OkHttpClient

class GlobalConfig: IGlobalConfig{
    val baseUrl = "https://api.github.com/"

    override fun configBaseUrl(): String = baseUrl

    override fun configOkHttpClient(application: Application, builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder
    }

}