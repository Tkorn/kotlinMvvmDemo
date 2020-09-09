package com.fyt.myapplication.mvvm.ui.uistate

import com.fyt.mvvm.base.BaseUiState

data class LoginUiState(var loginSuc: Boolean = false,
                        var showLoading: Boolean = false,
                        var toastMsg: String? = null) : BaseUiState(showLoading,toastMsg)