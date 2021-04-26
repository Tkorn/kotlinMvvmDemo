package com.fyt.myapplication.mvvm.ui.activity

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.fyt.mvvm.base.BaseVmActivity
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.WanAndroidViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WanAndroidActivity: BaseVmActivity<WanAndroidViewModel>() {
    override fun layoutId(): Int = R.layout.activity_wanandroid

    override fun initData(savedInstanceState: Bundle?) {
    }

}