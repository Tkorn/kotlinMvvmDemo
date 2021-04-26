package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.mvvm.base.BaseResult
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.common.Preference
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.WanAndroidRepository
import com.fyt.myapplication.mvvm.repository.bean.BaseResponse
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.myapplication.mvvm.ui.uistate.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application,
    repository: WanAndroidRepository,
    responseErrorListener: IResponseErrorListener
) : BaseViewModel<WanAndroidRepository>(application, repository, responseErrorListener) {

    var loginUiState = MutableLiveData<LoginUiState>()

    fun login(username:String, password:String) {
        showLoading.postValue(true)
        apply(object :ResultCallBack<BaseResponse<UserBean>>{
            override suspend fun callBack(): BaseResult<BaseResponse<UserBean>> {
                return mRepository.login(username,password)
            }
        },{
            showLoading.postValue(false)
            if(it.errorCode == 0){
                Preference.preferences.edit()
                    .putString("username",username)
                    .putString("password",password)
                    .apply()
                loginUiState.postValue(LoginUiState(loginSuc = true))
            }else{
                showToastMsg.postValue( it.errorMsg)
            }

        },{
            showToastMsg.postValue("登录失败")
            mResponseErrorListener.handleResponseError(it)
        })

    }

}
