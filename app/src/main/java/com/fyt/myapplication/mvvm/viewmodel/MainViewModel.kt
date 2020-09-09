package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.MainRepository
import com.fyt.myapplication.mvvm.ui.uistate.MainUiState

class MainViewModel(
    application: Application,
    private val mainRepository: MainRepository,
    responseErrorListener: IResponseErrorListener
) : BaseViewModel<MainRepository, MainUiState>(application, mainRepository, responseErrorListener) {

    private var mainUiState = MutableLiveData<MainUiState>(MainUiState())
    override fun getUiState(): LiveData<MainUiState> = mainUiState

}
