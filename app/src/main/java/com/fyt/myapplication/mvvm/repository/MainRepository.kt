package com.fyt.myapplication.mvvm.repository

import com.fyt.mvvm.base.BaseRepository
import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.myapplication.mvvm.repository.api.UserService
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.mvvm.base.BaseResult

class MainRepository(repositoryManager: IRepositoryManager): BaseRepository(repositoryManager){

    val USERS_PER_PAGE = 12

    suspend fun getUsers(lastIdQueried: Int): BaseResult<List<UserBean>> {
        return safeApiResponse(call = {
            mRepositoryManager!!.obtainRetrofitService(UserService::class.java)
                .getUsers(lastIdQueried, USERS_PER_PAGE)
        })
    }

}