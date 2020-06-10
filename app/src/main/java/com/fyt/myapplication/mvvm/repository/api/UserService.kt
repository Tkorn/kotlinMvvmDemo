package com.fyt.myapplication.mvvm.repository.api

import com.fyt.myapplication.mvvm.repository.bean.UserBean
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserService {

    companion object{
        const val HEADER_API_VERSION = "Accept: application/vnd.github.v3+json"
    }

    @Headers(value = [HEADER_API_VERSION])
    @GET("/users")
    suspend fun getUsers(@Query("since") lastIdQueried: Int, @Query("per_page") perPage: Int): Response<List<UserBean>>
}