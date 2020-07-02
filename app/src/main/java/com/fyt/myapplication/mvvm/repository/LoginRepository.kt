package com.fyt.myapplication.mvvm.repository

import com.fyt.mvvm.base.BaseRepository
import com.fyt.mvvm.globalsetting.RepositoryManager
import com.fyt.myapplication.mvvm.repository.api.UserService
import com.fyt.myapplication.mvvm.repository.bean.LoginRequestModel

class LoginRepository(repositoryManager: RepositoryManager): BaseRepository(repositoryManager) {

    suspend fun authorizations(loginRequestModel: LoginRequestModel,
                               client_id: String, fingerprint: String)
            = safeApiResponse(call = {
        mRepositoryManager!!.obtainRetrofitService(UserService::class.java)
            .authorizations(loginRequestModel,client_id,fingerprint)
    })
}