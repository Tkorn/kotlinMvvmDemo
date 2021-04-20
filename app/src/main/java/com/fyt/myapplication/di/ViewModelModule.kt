package com.fyt.myapplication.di

import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.myapplication.mvvm.repository.MainRepository
import com.fyt.myapplication.mvvm.repository.WanAndroidRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @ViewModelScoped
    @Provides
    fun provideMainRepository(repositoryManager: IRepositoryManager): MainRepository {
        return MainRepository(repositoryManager)
    }

    @ViewModelScoped
    @Provides
    fun provideWanAndroidRepository(repositoryManager: IRepositoryManager): WanAndroidRepository {
        return WanAndroidRepository(repositoryManager)
    }

}