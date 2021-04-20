package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.mvvm.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: BaseFragment<RegisterViewModel>() {
    override fun initViewModel(): RegisterViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initData(savedInstanceState: Bundle?) {

        observe(mViewModel.mUiState){

            if (it.loginSuc){
                //注册成功

            }
        }

    }
}