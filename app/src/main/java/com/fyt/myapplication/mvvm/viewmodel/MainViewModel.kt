package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.base.BaseResult
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.MainRepository
import com.fyt.myapplication.mvvm.viewmodel.uistate.MainUiState
import com.fyt.myapplication.mvvm.repository.bean.UserBean

class MainViewModel(
    application: Application,
    mainRepository: MainRepository,
    responseErrorListener: IResponseErrorListener
) : BaseViewModel<MainRepository, MainUiState>(application, mainRepository, responseErrorListener) {

    private var mainUiState = MutableLiveData<MainUiState>(MainUiState())
    override fun getUiState(): LiveData<MainUiState> = mainUiState

    private fun createDataSource(): DataSource<Long, UserBean> {
        return object : ItemKeyedDataSource<Long, UserBean>() {
            override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<UserBean>) {
                apply(object : ResultCallBack<List<UserBean>> {
                    override suspend fun callBack(): BaseResult<List<UserBean>> {
                        return mRepository.getUsers(1, params.requestedLoadSize)
                    }
                }, {
                    callback.onResult(it)
                    mainUiState.postNext { state ->
                        state.copy(referish = true, loadMode = false)
                    }
                }, { e ->
                    mResponseErrorListener.handleResponseError(e)
                    mainUiState.postNext { state ->
                        state.copy(referish = true, loadMode = false)
                    }
                })
            }

            override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<UserBean>) {
                apply(object : ResultCallBack<List<UserBean>> {
                    override suspend fun callBack(): BaseResult<List<UserBean>> {
                        return mRepository.getUsers(params.key, params.requestedLoadSize)
                    }
                }, {
                    callback.onResult(it)
                }, { e ->
                    mResponseErrorListener.handleResponseError(e)
                })
            }

            override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<UserBean>) {
            }

            override fun getKey(item: UserBean): Long {
                return item.id
            }

        }
    }

    val userLiveData =
        LivePagedListBuilder(mRepository.createDataSourceFactory(createDataSource()),
            mRepository.createConfig())
            .setInitialLoadKey(1)
            .build()

    fun onRefresh() {
        userLiveData.value!!.dataSource.invalidate()
    }

}
