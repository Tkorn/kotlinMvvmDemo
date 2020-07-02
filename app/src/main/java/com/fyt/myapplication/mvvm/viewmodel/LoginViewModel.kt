package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.mvvm.base.BaseResult
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.Constant
import com.fyt.myapplication.mvvm.repository.LoginRepository
import com.fyt.myapplication.mvvm.repository.bean.LoginRequestModel
import com.fyt.myapplication.mvvm.repository.bean.UserAccessToken
import com.fyt.myapplication.mvvm.viewmodel.uistate.LoginUiState

class LoginViewModel(application: Application,
                     loginRepository: LoginRepository,
                     responseErrorListener: IResponseErrorListener
): BaseViewModel<LoginRepository,LoginUiState>(application,loginRepository,responseErrorListener) {

    private val loginUiState: MutableLiveData<LoginUiState> = MutableLiveData(LoginUiState())
    override fun getUiState(): LiveData<LoginUiState> = loginUiState

    fun login(name: String, password: String) {

        apply(object: ResultCallBack<UserAccessToken>{
            override suspend fun callBack(): BaseResult<UserAccessToken> {
                return mRepository.authorizations(
                    LoginRequestModel(
                        Constant.CLIENT_SECRET,
                        Constant.SCOPE,
                        Constant.NOTE, Constant.NOTE_URL
                    ),
                    Constant.CLIENT_ID,
                    Constant.FINGERPRINT)
            }
        },{

        },{})



    }


}