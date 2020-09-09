package com.fyt.myapplication.mvvm.repository

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.fyt.mvvm.base.BaseRepository
import com.fyt.mvvm.globalsetting.IRepositoryManager
import com.fyt.myapplication.mvvm.repository.api.UserService
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.mvvm.base.BaseResult

class MainRepository(repositoryManager: IRepositoryManager): BaseRepository(repositoryManager){

    suspend fun getUsers(lastIdQueried: Long, pageSize: Int): BaseResult<List<UserBean>> {
        return safeApiResponse(call = {
            mRepositoryManager!!.obtainRetrofitService(UserService::class.java)
                .getUsers(lastIdQueried, pageSize)
        })
    }

}