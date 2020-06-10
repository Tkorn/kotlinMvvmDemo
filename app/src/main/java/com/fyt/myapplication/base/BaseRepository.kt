package com.fyt.myapplication.base
import com.fyt.myapplication.base.globalsetting.RepositoryManager
import com.fyt.myapplication.mvvm.repository.bean.Result
import retrofit2.HttpException
import retrofit2.Response

open class BaseRepository(repositoryManager: RepositoryManager) {
    var mRepositoryManager: RepositoryManager? = repositoryManager

    suspend fun <T: Any> safeApiResponse(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(HttpException(response))
            }
        } catch (e:Exception) {
            Result.Error(e)
        }
    }

    /**
     * 在框架中 {@link BaseViewModel#onCleared()} 时会默认调用
     */
    open fun onCleared(){
        mRepositoryManager = null
    }
}