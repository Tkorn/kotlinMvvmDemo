package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import com.fyt.myapplication.mvvm.viewmodel.WanAndroidViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class LaunchFragment: BaseFragment<LoginViewModel>(){
    override fun initViewModel(): LoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_login,container,false)

    override fun initData(savedInstanceState: Bundle?) {
        Timer().schedule(3000){
            lifecycleScope.launch(Dispatchers.Main){
                findNavController().navigate(R.id.action_launchFragment_to_loginFragment)
            }
        }
    }

}