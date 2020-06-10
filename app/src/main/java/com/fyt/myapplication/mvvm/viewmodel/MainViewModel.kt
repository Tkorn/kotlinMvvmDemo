package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fyt.myapplication.base.BaseViewModel
import com.fyt.myapplication.base.globalsetting.GlobalResponseErrorListener
import com.fyt.myapplication.mvvm.repository.MainRepository
import com.fyt.myapplication.mvvm.viewmodel.uistate.MainUiState
import com.fyt.myapplication.mvvm.repository.bean.Result
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.google.gson.Gson
import timber.log.Timber

class MainViewModel(application: Application,
                    mainRepository: MainRepository) : BaseViewModel<MainRepository,MainUiState>(application,mainRepository) {

    private var mainUiState = MutableLiveData<MainUiState>(MainUiState())
    override fun getUiState(): LiveData<MainUiState> = mainUiState

    var lastUserId = 1

    fun onRefresh() {
        lastUserId = 1
        apply(object : ResultCallBack<List<UserBean>> {
            override suspend fun callBack(): Result<List<UserBean>> {
                return mRepository.getUsers(lastUserId)
            }
        },{
//            Timber.e(Gson().toJson(it))
            var users = mutableListOf<UserBean>()
            users.addAll(it)
            if (users.size > 0) {
                lastUserId = users[users.size -1].id
            }
            mainUiState.postNext {state->
                state.copy(referish = true,loadMode = false,userList = users)
            }
        },{e ->
            GlobalResponseErrorListener.getInstance().handleResponseError(getApplication(),e)
            mainUiState.postNext {state->
                state.copy(referish = true,loadMode = false)
            }
        })
    }


    fun onLoadMore(){
        apply(object : ResultCallBack<List<UserBean>> {
            override suspend fun callBack(): Result<List<UserBean>> {
                return mRepository.getUsers(lastUserId)
            }
        },{
//            Timber.e(Gson().toJson(it))
            var users = mutableListOf<UserBean>()
            users.addAll(it)
            if (users.size > 0) {
                lastUserId = users[users.size -1].id
            }
            mainUiState.postNext {state->
                state.copy(loadMode = true,referish = false,userList = users)
            }
        },{e ->
            GlobalResponseErrorListener.getInstance().handleResponseError(getApplication(),e)
            mainUiState.postNext {state->
                state.copy(loadMode = true,referish = false)
            }
        })
    }

}
