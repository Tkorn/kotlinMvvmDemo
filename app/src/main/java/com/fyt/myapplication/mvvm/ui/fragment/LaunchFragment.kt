package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.databinding.FragmentLaunchBinding
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class LaunchFragment: BaseFragment<LoginViewModel, FragmentLaunchBinding>(){
    override fun layoutId(): Int = R.layout.fragment_launch

    override fun initData(savedInstanceState: Bundle?) {
        Timer().schedule(3000){
            lifecycleScope.launch(Dispatchers.Main){
                findNavController().navigate(R.id.action_launchFragment_to_loginFragment)
            }
        }
    }

}