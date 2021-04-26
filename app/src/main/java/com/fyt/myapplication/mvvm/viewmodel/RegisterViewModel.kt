package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.mvvm.base.BaseResult
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.myapplication.GlobalResponseErrorListenerImpl
import com.fyt.myapplication.mvvm.repository.WanAndroidRepository
import com.fyt.myapplication.mvvm.repository.bean.BaseResponse
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.myapplication.mvvm.ui.uistate.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(application: Application,
                                            wanAndroidRepository: WanAndroidRepository,
                                            responseErrorListener: GlobalResponseErrorListenerImpl):
    BaseViewModel<WanAndroidRepository>(application,wanAndroidRepository,responseErrorListener) {


    fun register(account: String, password: String,repassword: String){
        showLoading.postValue( true)
        apply(object : ResultCallBack<BaseResponse<UserBean>> {
            override suspend fun callBack(): BaseResult<BaseResponse<UserBean>> {
                return mRepository.register(account,password,repassword)
            }
        },{
            //TODO 注册成功
        },{
            //TODO 注册失败
        })

    }

}