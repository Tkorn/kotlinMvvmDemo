package com.fyt.myapplication.mvvm.viewmodel.uistate

import com.fyt.myapplication.base.BaseUiState
import com.fyt.myapplication.mvvm.repository.bean.UserBean

data class MainUiState(var referish: Boolean = false,
                  var loadMode: Boolean = false,
                  var userList: MutableList<UserBean> = mutableListOf(),
                  var showLoading: Boolean = false,
                  var toastMsg: String? = null) : BaseUiState(showLoading,toastMsg)