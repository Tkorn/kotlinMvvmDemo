package com.fyt.myapplication.di

import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.myapplication.mvvm.repository.WanAndroidRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

}