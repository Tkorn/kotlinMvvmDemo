package com.fyt.myapplication.mvvm.ui.activity

import android.os.Bundle
import com.fyt.mvvm.base.BaseActivity
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.WanAndroidViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WanAndroidActivity: BaseActivity<WanAndroidViewModel>() {
    override fun initViewModel(): WanAndroidViewModel = getViewModel()

    override fun initView(savedInstanceState: Bundle?): Int = R.layout.activity_wanandroid

    override fun initData(savedInstanceState: Bundle?) {

    }
}