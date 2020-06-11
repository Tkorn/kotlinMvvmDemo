package com.fyt.myapplication.mvvm.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import com.fyt.mvvm.base.BaseActivity
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity: BaseActivity<LoginViewModel>(){

    var progressDialog: ProgressDialog?= null

    override fun initViewModel(): LoginViewModel = getViewModel()

    override fun initView(savedInstanceState: Bundle?): Int = R.layout.login_activity

    override fun initData(savedInstanceState: Bundle?) {


    }

    override fun showLoading() {
        if (progressDialog == null){
            progressDialog = ProgressDialog(this)
        }
        progressDialog!!.show()
    }

    override fun hideLoading() {
        if (progressDialog != null){
            progressDialog!!.hide()
        }
    }
}