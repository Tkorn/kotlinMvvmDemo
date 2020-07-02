package com.fyt.myapplication.mvvm.repository.api

import com.fyt.myapplication.mvvm.repository.bean.LoginRequestModel
import com.fyt.myapplication.mvvm.repository.bean.UserAccessToken
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    companion object{
        const val HEADER_API_VERSION = "Accept: application/vnd.github.v3+json"
    }

    @PUT("authorizations/clients/{client_id}/{fingerprint}")
    suspend fun authorizations(
        @Body loginRequestModel: LoginRequestModel,
        @Path("client_id") client_id: String,
        @Path("fingerprint") fingerprint: String):  Response<UserAccessToken>


    @Headers(value = [HEADER_API_VERSION])
    @GET("/users")
    suspend fun getUsers(@Query("since") lastIdQueried: Long, @Query("per_page") perPage: Int): Response<List<UserBean>>
}