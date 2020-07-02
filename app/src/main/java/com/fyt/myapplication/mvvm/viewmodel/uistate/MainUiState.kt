package com.fyt.myapplication.mvvm.viewmodel.uistate

import com.fyt.mvvm.base.BaseUiState

data class MainUiState(var referish: Boolean = false,
                  var loadMode: Boolean = false,
                  var showLoading: Boolean = false,
                  var toastMsg: String? = null) : BaseUiState(showLoading,toastMsg)