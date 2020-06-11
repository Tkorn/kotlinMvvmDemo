package com.fyt.mvvm.di

import com.fyt.mvvm.BuildConfig
import com.fyt.mvvm.globalsetting.IGlobalConfig
import com.fyt.mvvm.globalsetting.IGlobalHttpInterceptor
import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.mvvm.globalsetting.RepositoryManager
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


val TIME_OUT : Long = 10

val mSingleModule = module {

    single {
        GsonBuilder()
            //支持序列化值为 null 的参数
            .serializeNulls()
            //支持将序列化 key 为 Object 的 Map, 默认只能序列化 key 为 String 的 Map
            .enableComplexMapKeySerialization()
            .create()
    }

    single {
        val globalConfig: IGlobalConfig = get()
        val globalHttpInterceptor: IGlobalHttpInterceptor = get()
        var builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            })
            .addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(globalHttpInterceptor.onHttpRequestBefore(chain,chain.request()))
                }

            })
            .addNetworkInterceptor(object : Interceptor{
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
        globalConfig.configOkHttpClient(androidApplication(),builder).build()

     }

    single {
        val globalConfig: IGlobalConfig = get()
        Retrofit.Builder()
            .client(get())
            .baseUrl(globalConfig.configBaseUrl())//BuildConfig.API_BASE_URL
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<IRepositoryManager> { RepositoryManager(get(), androidApplication()) }

}
