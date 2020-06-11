package com.fyt.mvvm.globalsetting

import android.app.Application
import okhttp3.OkHttpClient

interface IGlobalConfig{

    fun configBaseUrl():String

    fun configOkHttpClient(application: Application, builder: OkHttpClient.Builder): OkHttpClient.Builder
}