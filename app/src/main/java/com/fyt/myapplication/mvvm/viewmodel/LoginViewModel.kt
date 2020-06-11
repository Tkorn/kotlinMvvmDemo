package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.LoginRepository
import com.fyt.myapplication.mvvm.viewmodel.uistate.LoginUiState

class LoginViewModel(application: Application,
                     loginRepository: LoginRepository,
                     responseErrorListener: IResponseErrorListener
): BaseViewModel<LoginRepository,LoginUiState>(application,loginRepository,responseErrorListener) {

    private val loginUiState: MutableLiveData<LoginUiState> = MutableLiveData(LoginUiState())
    override fun getUiState(): LiveData<LoginUiState> = loginUiState


}