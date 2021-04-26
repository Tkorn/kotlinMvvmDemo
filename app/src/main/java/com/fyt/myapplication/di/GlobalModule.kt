package com.fyt.myapplication.di

import com.fyt.mvvm.globalsetting.IView
import com.fyt.mvvm.globalsetting.IGlobalConfiguration
import com.fyt.mvvm.globalsetting.IGlobalHttpInterceptor
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.GlobalConfigurationImpl
import com.fyt.myapplication.GlobalHttpInterceptorImpl
import com.fyt.myapplication.GlobalResponseErrorListenerImpl
import com.fyt.myapplication.IViewImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GlobalModule {
    @Binds
    abstract fun provideIGlobalConfiguration(impl: GlobalConfigurationImpl):IGlobalConfiguration

    @Binds
    abstract fun provideIGlobalHttpInterceptor(impl: GlobalHttpInterceptorImpl): IGlobalHttpInterceptor

    @Binds
    abstract fun provideIResponseErrorListener(impl:GlobalResponseErrorListenerImpl):IResponseErrorListener

    @Binds
    abstract fun provideIView(impl: IViewImpl): IView
}