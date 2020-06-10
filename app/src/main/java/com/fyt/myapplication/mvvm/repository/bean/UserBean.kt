package com.fyt.myapplication.mvvm.repository.bean

data class UserBean(val id: Int,
               val login: String,
               val avatar_url: String) {
    override fun toString(): String {
        return "id -> $id login -> $login"
    }
}