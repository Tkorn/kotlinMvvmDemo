package com.fyt.mvvm.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fyt.mvvm.common.ClassUtil

abstract class BaseFragment<VM: BaseViewModel<out BaseRepository>,DB: ViewDataBinding>: BaseVmFragment<VM>(){

    lateinit var mDataBinding: DB

    override fun initDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDataBinding = DataBindingUtil.inflate(inflater,layoutId(),container,false)
        mDataBinding.lifecycleOwner = this
        return mDataBinding.root
    }

}