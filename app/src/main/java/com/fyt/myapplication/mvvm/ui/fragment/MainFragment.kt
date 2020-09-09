package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import com.fyt.myapplication.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_launch.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainFragment: BaseFragment<MainViewModel>(){
    override fun initViewModel(): MainViewModel = getViewModel()

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_main,container,false)

    override fun initData(savedInstanceState: Bundle?) {

    }

}