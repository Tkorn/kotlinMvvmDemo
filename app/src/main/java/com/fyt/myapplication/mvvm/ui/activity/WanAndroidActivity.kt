package com.fyt.myapplication.mvvm.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.fyt.mvvm.base.BaseActivity
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.WanAndroidViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WanAndroidActivity: BaseActivity<WanAndroidViewModel>() {

    override fun initViewModel() = ViewModelProvider(this).get(WanAndroidViewModel::class.java)

    override fun initView(savedInstanceState: Bundle?): Int = R.layout.activity_wanandroid

    override fun initData(savedInstanceState: Bundle?) {}
}