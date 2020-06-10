package com.fyt.myapplication.base

import android.app.Application
import android.content.ComponentCallbacks2
import com.bumptech.glide.Glide
import com.fyt.myapplication.BuildConfig
import com.fyt.myapplication.common.Preference
import com.fyt.myapplication.di.component.appModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            if (BuildConfig.DEBUG){
                androidLogger()
            }
            // use the Android context given there
            androidContext(this@BaseApplication)
            modules(appModule)

        }

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
            Timber.plant(Timber.DebugTree())
        }
        Preference.setContext(applicationContext)
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