package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    mainRepository: MainRepository,
    responseErrorListener: IResponseErrorListener
) : BaseViewModel<MainRepository>(application, mainRepository, responseErrorListener) {

}
