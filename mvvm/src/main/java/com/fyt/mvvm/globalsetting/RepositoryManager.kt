package com.fyt.mvvm.globalsetting

import android.app.Application
import android.content.Context
import retrofit2.Retrofit

class RepositoryManager(retrofit:Retrofit, application: Application): IRepositoryManager {
    var mRetrofit: Retrofit = retrofit
    var mApplication: Application = application

    override fun getContext(): Context {
        return mApplication
    }

    override fun <T> obtainRetrofitService(service: Class<T>): T {
        return mRetrofit.create(service)
    }

    override fun <T> obtainCacheService(cache: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllCache() {
    }


}