package com.fyt.mvvm.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VM: BaseViewModel<out BaseRepository>,DB: ViewDataBinding>: BaseVmActivity<VM>() {

    lateinit var mDataBinding: DB


    override fun initDataBinding(): Boolean {
        mDataBinding = DataBindingUtil.setContentView(this,layoutId())
        mDataBinding.lifecycleOwner = this
        return true
    }

}