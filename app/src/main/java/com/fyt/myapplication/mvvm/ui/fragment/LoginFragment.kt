package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginFragment: BaseFragment<LoginViewModel>() {
    override fun initViewModel(): LoginViewModel = getViewModel()

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.login_fragment,container,false)

    override fun initData(savedInstanceState: Bundle?) {

    }
}