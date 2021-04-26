package com.fyt.mvvm.di

import android.content.Context
import com.fyt.mvvm.BuildConfig
import com.fyt.mvvm.cache.Cache
import com.fyt.mvvm.cache.LruCache
import com.fyt.mvvm.globalsetting.IGlobalConfiguration
import com.fyt.mvvm.globalsetting.IGlobalHttpInterceptor
import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.mvvm.globalsetting.RepositoryManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingleModule {
    private val TIME_OUT : Long = 10

    @Singleton
    @Provides
    fun provideGson(configuration:IGlobalConfiguration): Gson {
        var builder =  GsonBuilder()
            //支持序列化值为 null 的参数
            .serializeNulls()
            //支持将序列化 key 为 Object 的 Map, 默认只能序列化 key 为 String 的 Map
            .enableComplexMapKeySerialization()
        configuration.configGson(builder)
        return builder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(configuration: IGlobalConfiguration,
                            globalHttpInterceptor: IGlobalHttpInterceptor):OkHttpClient{
        var builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            })
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(globalHttpInterceptor.onHttpRequestBefore(chain,chain.request()))
                }

            })
            .addNetworkInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    var originalResponse: Response?
                    try {
                        originalResponse = chain.proceed(chain.request())
                    } catch (e: Exception) {
                        Timber.w("Http Error: $e")
                        throw e
                    }
                    return globalHttpInterceptor.onHttpResultResponse(chain,originalResponse)
                }
            })
        configuration.configOkHttpClient(builder)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson:Gson,
                        configuration:IGlobalConfiguration):Retrofit{
        var builder = Retrofit.Builder()
            .baseUrl(configuration.configBaseUrl())//域名
            .client(okHttpClient)//设置okhttp
            .addConverterFactory(GsonConverterFactory.create(gson))//使用 Gson
        configuration.configRetrofit(builder)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideCacheFactory(@ApplicationContext context: Context): Cache.Factory{
        return Cache.Factory {
            LruCache<Any,Any>(it.calculateCacheSize(context))
        }
    }

}
@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule{

    @Binds
    abstract fun bindIRepositoryManager(repositoryManager: RepositoryManager):IRepositoryManager

}

