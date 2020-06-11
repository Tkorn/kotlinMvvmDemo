package com.fyt.myapplication.mvvm.viewmodel.uistate

import com.fyt.mvvm.base.BaseUiState

data class LoginUiState(var showLoading: Boolean = false,
                        var toastMsg: String? = null): BaseUiState()