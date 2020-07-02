package com.fyt.myapplication.mvvm.repository.bean

data class UserBean(val id: Long,
               var login: String,
               var avatar_url: String) {
    override fun toString(): String {
        return "id -> $id login -> $login"
    }
}