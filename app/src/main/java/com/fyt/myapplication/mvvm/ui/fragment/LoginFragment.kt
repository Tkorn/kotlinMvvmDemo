package com.fyt.myapplication.mvvm.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.navigation.fragment.findNavController
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.databinding.FragmentLoginBinding
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: BaseFragment<LoginViewModel,FragmentLoginBinding>(){
    override fun layoutId(): Int = R.layout.fragment_login

    override fun initData(savedInstanceState: Bundle?) {

        observer(mViewModel.loginUiState){
            if(it.loginSuc){
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }
        mDataBinding.btnLogin.setOnClickListener {
            val account = mDataBinding.etAccount .text.toString()
            val password = mDataBinding.etPassword.text.toString()
            if (TextUtils.isEmpty(account)){
                showMessage("账号不能为空")
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)){
                showMessage("密码不能为空")
                return@setOnClickListener
            }
            mViewModel.login(account,password)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoading()
    }


}