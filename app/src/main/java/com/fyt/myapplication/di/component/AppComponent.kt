package com.fyt.myapplication.di.component

import com.fyt.myapplication.base.globalsetting.GlobalHttpInterceptor
import com.fyt.myapplication.base.globalsetting.RepositoryManager
import com.fyt.myapplication.mvvm.repository.MainRepository
import com.fyt.myapplication.mvvm.viewmodel.MainViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val TIME_OUT : Long = 10
val BASE_URL = "https://api.github.com"

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
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .addInterceptor(GlobalHttpInterceptor())
            .build()

     }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single { RepositoryManager(get(), get()) }

}


val viewModelModule = module {
    viewModel { MainViewModel(get(),MainRepository(get())) }
}

val appModule = listOf(mSingleModule, viewModelModule)