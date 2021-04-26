package com.fyt.myapplication

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import com.fyt.mvvm.globalsetting.IView
import javax.inject.Inject

class IViewImpl @Inject constructor() : IView {
    private var progressDialog: ProgressDialog? = null

    override fun showLoading(context: Context) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
        }
        progressDialog?.show()
    }

    override fun hideLoading() {
        progressDialog?.dismiss()
    }

    override fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}