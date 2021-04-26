package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.databinding.FragmentMainBinding
import com.fyt.myapplication.mvvm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment: BaseFragment<MainViewModel,FragmentMainBinding>(){
    override fun layoutId(): Int = R.layout.fragment_main

    override fun initData(savedInstanceState: Bundle?) {

    }

}