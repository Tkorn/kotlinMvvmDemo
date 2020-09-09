package com.fyt.myapplication.mvvm.repository.api

import com.fyt.myapplication.mvvm.repository.bean.BaseResponse
import com.fyt.myapplication.mvvm.repository.bean.LoginRequestModel
import com.fyt.myapplication.mvvm.repository.bean.UserAccessToken
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import retrofit2.Response
import retrofit2.http.*

interface WanAndroidApi {

    companion object{
        const val HEADER_API_VERSION = "Accept: application/vnd.github.v3+json"
    }

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String):  Response<BaseResponse<UserBean>>


    @Headers(value = [HEADER_API_VERSION])
    @GET("/users")
    suspend fun getUsers(@Query("since") lastIdQueried: Long, @Query("per_page") perPage: Int): Response<List<UserBean>>
}