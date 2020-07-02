package com.fyt.myapplication

import android.app.Application
import com.fyt.mvvm.globalsetting.IGlobalConfig
import okhttp3.OkHttpClient

class GlobalConfig: IGlobalConfig{
    val baseUrl = "https://api.github.com/"

    override fun configBaseUrl(): String = baseUrl

    /*
    * 添加 OkHttpClient.Builder 的设置
    * */
    override fun configOkHttpClient(application: Application, builder: OkHttpClient.Builder): OkHttpClient.Builder {
        return builder
    }

}