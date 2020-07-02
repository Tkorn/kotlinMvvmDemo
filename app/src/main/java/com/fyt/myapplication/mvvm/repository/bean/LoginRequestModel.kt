package com.fyt.myapplication.mvvm.repository.bean

data class LoginRequestModel(
    val client_secret: String,
    val scopes: List<String>,
    val note: String,
    val note_url: String)