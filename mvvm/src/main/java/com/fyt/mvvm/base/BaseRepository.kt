package com.fyt.mvvm.base

import retrofit2.HttpException
import retrofit2.Response
import com.fyt.mvvm.globalsetting.IRepositoryManager
import kotlinx.coroutines.delay

open class BaseRepository(repositoryManager: IRepositoryManager) {
    var mRepositoryManager: IRepositoryManager? = repositoryManager

    suspend fun <T: Any> safeApiResponse(call: suspend () -> Response<T>): BaseResult<T> {
        return try {
            serviceCall(call,0)
        } catch (e:Exception) {
            BaseResult.Error(e)
        }
    }

    suspend fun <T: Any> safeApiResponse(call: suspend () -> Response<T>,retry: Int): BaseResult<T> {
        return try {
            serviceCall(call,retry)
        } catch (e:Exception) {
            BaseResult.Error(e)
        }
    }

    private suspend fun <T: Any> serviceCall(call: suspend () -> Response<T>, count: Int): BaseResult<T>{
        val response = call()
        return if (response.isSuccessful) {
            BaseResult.Success(response.body()!!)
        } else {
            if (count == 0){
                BaseResult.Error(HttpException(response))
            }else{
                val next = count - 1
                delay(1000L)
                serviceCall(call, next)
            }
        }
    }

    /**
     * 在框架中 {@link BaseViewModel#onCleared()} 时会默认调用
     */
    open fun onCleared(){
        mRepositoryManager = null
    }
}