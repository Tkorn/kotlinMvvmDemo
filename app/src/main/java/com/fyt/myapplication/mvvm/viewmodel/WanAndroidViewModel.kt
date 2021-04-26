package com.fyt.myapplication.mvvm.viewmodel

import android.app.Application
import com.fyt.mvvm.base.BaseViewModel
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.fyt.myapplication.mvvm.repository.WanAndroidRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WanAndroidViewModel @Inject constructor(
    application: Application,
    repository: WanAndroidRepository,
    responseErrorListener: IResponseErrorListener
): BaseViewModel<WanAndroidRepository>(application,repository, responseErrorListener) {


}