package com.fyt.mvvm.base

import android.app.Application
import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import com.fyt.mvvm.common.BaseResult
import com.fyt.mvvm.globalsetting.IResponseErrorListener

abstract class BaseViewModel<R: BaseRepository,US: BaseUiState>(application: Application, repository: R,responseErrorListener: IResponseErrorListener): AndroidViewModel(application) {
    var mRepository = repository
    val mResponseErrorListener = responseErrorListener

    val mUiState: LiveData<US>
        get() = getUiState()

    abstract fun getUiState():LiveData<US>

    override fun onCleared() {
        super.onCleared()
        mRepository.onCleared()
    }

    fun <T> apply(resultCallBack: ResultCallBack<T>, success:(r :T)->Unit){
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO){
                resultCallBack.callBack()
            }
            Timber.e(result.toString())
            when (result){
                is BaseResult.Success -> success(result.data)
                is BaseResult.Error -> mResponseErrorListener.handleResponseError(result.error)
            }
        }
    }

    fun <T> apply(resultCallBack: ResultCallBack<T>, success:(r :T)->Unit,error:(e :Throwable)->Unit){
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO){
                resultCallBack.callBack()
            }
            when (result){
                is BaseResult.Success -> success(result.data)
                is BaseResult.Error -> error(result.error)
            }
        }
    }

    interface ResultCallBack<T>{
        suspend fun callBack(): BaseResult<T>
    }

    @AnyThread
    inline fun <reified T> MutableLiveData<T>.postNext(map: (T) -> T) {
        postValue(map(verifyLiveDataNotEmpty()))
    }

    @MainThread
    inline fun <reified T> MutableLiveData<T>.setNext(map: (T) -> T) {
        value = map(verifyLiveDataNotEmpty())
    }

    @AnyThread
    inline fun <reified T> LiveData<T>.verifyLiveDataNotEmpty(): T {
        return value
            ?: throw NullPointerException("MutableLiveData<${T::class.java}> not contain value.")
    }

}