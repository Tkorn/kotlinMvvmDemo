package com.fyt.myapplication.di

import com.fyt.mvvm.globalsetting.IGlobalConfig
import com.fyt.mvvm.globalsetting.IGlobalHttpInterceptor
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.GlobalConfig
import com.fyt.myapplication.GlobalHttpInterceptor
import com.fyt.myapplication.GlobalResponseErrorListener
import com.fyt.myapplication.mvvm.repository.LoginRepository
import com.fyt.myapplication.mvvm.repository.MainRepository
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import com.fyt.myapplication.mvvm.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


val mAppModule = module {

    single<IGlobalConfig>{ GlobalConfig() }

    single<IResponseErrorListener> { GlobalResponseErrorListener(androidContext()) }

    single<IGlobalHttpInterceptor> { GlobalHttpInterceptor() }

}


val mViewModelModule = module {
    viewModel { LoginViewModel(get(), LoginRepository(get()), get()) }
    viewModel { MainViewModel(get(), MainRepository(get()), get())}
}