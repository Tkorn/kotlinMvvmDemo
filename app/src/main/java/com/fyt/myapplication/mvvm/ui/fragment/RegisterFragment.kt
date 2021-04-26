package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.databinding.FragmentRegisterBinding
import com.fyt.myapplication.mvvm.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: BaseFragment<RegisterViewModel,FragmentRegisterBinding>() {
    override fun layoutId(): Int = R.layout.fragment_register

    override fun initData(savedInstanceState: Bundle?) {

    }
}