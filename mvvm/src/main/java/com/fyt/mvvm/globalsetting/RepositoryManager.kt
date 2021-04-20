package com.fyt.mvvm.globalsetting

import android.app.Application
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryManager @Inject constructor(retrofit:Retrofit, @ApplicationContext context: Context): IRepositoryManager {
    var mRetrofit: Retrofit = retrofit
    var mContext: Context = context

    override fun getContext(): Context {
        return mContext
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