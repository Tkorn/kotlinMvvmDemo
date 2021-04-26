package com.fyt.mvvm.globalsetting

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fyt.mvvm.cache.Cache
import com.fyt.mvvm.cache.CacheType
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryManager @Inject constructor(retrofit:Retrofit, @ApplicationContext context: Context): IRepositoryManager {
    var mRetrofit: Retrofit = retrofit
    var mContext: Context = context
    @Inject
    lateinit var cacheFactory: Cache.Factory

    private var retrofitCache: Cache<Any,Any>? =null
    private var roomCache: Cache<Any,Any>? =null

    override fun getContext(): Context {
        return mContext
    }

    override fun <T> obtainRetrofitService(service: Class<T>): T {
        if (retrofitCache == null){
            retrofitCache = cacheFactory.build(CacheType.RETROFIT_SERVICE_CACHE)
        }
        var retrofitService:T? = retrofitCache?.get(service.canonicalName) as T
        if (retrofitService == null){
            retrofitService = mRetrofit.create(service)
            retrofitCache?.put(service.canonicalName,retrofitService)
        }
        return retrofitService!!
    }

    override fun <T:RoomDatabase > obtainDataBase(database: Class<T>): T {
        if (roomCache == null){
            roomCache = cacheFactory.build(CacheType.CACHE_SERVICE_CACHE)
        }

        var roomDatabase:T? = roomCache?.get(database.canonicalName) as T
        if (roomDatabase == null){
            roomDatabase = Room.databaseBuilder(mContext,database,database.name).build()
            roomCache?.put(database.canonicalName,roomDatabase)
        }
        return roomDatabase
    }

    override fun clearAllCache() {
    }


}