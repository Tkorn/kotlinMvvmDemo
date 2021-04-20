package com.fyt.myapplication

import android.app.Application
import android.content.ComponentCallbacks2
import com.bumptech.glide.Glide
import com.fyt.mvvm.common.Preference
import com.fyt.mvvm.globalsetting.IGlobalConfiguration
import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.mvvm.globalsetting.RepositoryManager
import com.fyt.myapplication.mvvm.repository.api.WanAndroidApi
import com.fyt.myapplication.mvvm.repository.bean.UserAccessToken
import com.google.gson.Gson
import com.squareup.leakcanary.LeakCanary
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {
    @Inject lateinit var globalConfiguration: IGlobalConfiguration
    @Inject lateinit var repositoryManager: IRepositoryManager

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
            Timber.plant(Timber.DebugTree())
        }
        Preference.setContext(applicationContext)
        globalConfiguration.configBaseUrl()
        repositoryManager.obtainRetrofitService(WanAndroidApi::class.java)
    }


    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        // clear Glide cache
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        // trim memory
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        // low memory clear Glide cache
        Glide.get(this).clearMemory()
    }
}