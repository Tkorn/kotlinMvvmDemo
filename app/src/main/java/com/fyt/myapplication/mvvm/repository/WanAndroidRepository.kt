package com.fyt.myapplication.mvvm.repository

import com.fyt.mvvm.base.BaseRepository
import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.mvvm.base.BaseResult
import com.fyt.myapplication.mvvm.repository.api.WanAndroidApi
import com.fyt.myapplication.mvvm.repository.bean.BaseResponse
import javax.inject.Inject

class WanAndroidRepository(repositoryManager: IRepositoryManager): BaseRepository(repositoryManager){

    suspend fun login(username: String, password: String): BaseResult<BaseResponse<UserBean>> {
        return safeApiResponse(call = {
            mRepositoryManager!!.obtainRetrofitService(WanAndroidApi::class.java)
                .login(username, password)
        })
    }

    suspend fun register(username: String, password: String, repassword: String): BaseResult<BaseResponse<UserBean>> {
        return safeApiResponse(call = {
            mRepositoryManager!!.obtainRetrofitService(WanAndroidApi::class.java)
                .register(username, password,repassword)
        })
    }

}