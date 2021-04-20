package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.mvvm.base.BaseUiState
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.WanAndroidRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class WanAndroidViewModel @Inject constructor(
    application: Application,
    repository: WanAndroidRepository,
    responseErrorListener: IResponseErrorListener
): BaseViewModel<WanAndroidRepository,BaseUiState>(application,repository, responseErrorListener) {
    override fun getUiState(): LiveData<BaseUiState> = MutableLiveData(BaseUiState())


}