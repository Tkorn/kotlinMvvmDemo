package com.fyt.mvvm.base

import retrofit2.HttpException
import retrofit2.Response
import com.fyt.mvvm.globalsetting.IRepositoryManager

open class BaseRepository(repositoryManager: IRepositoryManager) {
    var mRepositoryManager: IRepositoryManager? = repositoryManager

    suspend fun <T: Any> safeApiResponse(call: suspend () -> Response<T>): BaseResult<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                BaseResult.Success(response.body()!!)
            } else {
                BaseResult.Error(HttpException(response))
            }
        } catch (e:Exception) {
            BaseResult.Error(e)
        }
    }

    /**
     * 在框架中 {@link BaseViewModel#onCleared()} 时会默认调用
     */
    open fun onCleared(){
        mRepositoryManager = null
    }
}