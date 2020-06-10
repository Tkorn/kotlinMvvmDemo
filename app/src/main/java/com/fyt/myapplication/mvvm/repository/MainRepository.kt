package com.fyt.myapplication.mvvm.repository

import com.fyt.myapplication.base.BaseRepository
import com.fyt.myapplication.base.globalsetting.RepositoryManager
import com.fyt.myapplication.mvvm.repository.api.UserService
import com.fyt.myapplication.mvvm.repository.bean.Result
import com.fyt.myapplication.mvvm.repository.bean.UserBean

class MainRepository(repositoryManager: RepositoryManager): BaseRepository(repositoryManager){

    val USERS_PER_PAGE = 12

    suspend fun getUsers(lastIdQueried: Int): Result<List<UserBean>> {
        return safeApiResponse(call = {
            mRepositoryManager!!.obtainRetrofitService(UserService::class.java)
                .getUsers(lastIdQueried, USERS_PER_PAGE)
        })
    }

}