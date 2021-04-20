package com.fyt.myapplication.mvvm.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.fyt.mvvm.base.BaseFragment
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_launch.*

@AndroidEntryPoint
class LoginFragment: BaseFragment<LoginViewModel>(){
    override fun initViewModel(): LoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_launch,container,false)

    override fun initData(savedInstanceState: Bundle?) {

        observe(mViewModel.mUiState){
            if(it.loginSuc){
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }

        btn_login.setOnClickListener {
            val account = et_account.text.toString()
            val password = et_password.text.toString()
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

    private var progressDialog: ProgressDialog? = null

    override fun showLoading() {
        if (progressDialog == null){
            progressDialog = ProgressDialog(context)
        }
        progressDialog?.show()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoading()
    }

}