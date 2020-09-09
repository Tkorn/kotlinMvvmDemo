package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.concurrent.schedule

class LaunchFragment: BaseFragment<LoginViewModel>(){
    override fun initViewModel(): LoginViewModel = getViewModel()

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_login,container,false)

    override fun initData(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            Timer().schedule(3000){
                findNavController().navigate(R.id.action_launchFragment_to_loginFragment)
            }
        }
    }

}