package com.fyt.mvvm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseFragment<VM: BaseViewModel<out BaseRepository, out BaseUiState>>: Fragment(), IFragment{

    lateinit var mViewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return initView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = initViewModel()
        initData(savedInstanceState)

        observe(mViewModel.mUiState) { state ->
            if (state.loading){
                showLoading()
            }else{
                hideLoading()
            }
            state.showToastMsg?.let {
                showMessage(it)
            }
        }
    }

    fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: (t: T) -> Unit) {
        liveData.observe(this, Observer { it?.let { t -> observer(t) } })
    }

    abstract fun initViewModel(): VM

    override fun showMessage(message: String) {
        Toast.makeText(this.context,message, Toast.LENGTH_SHORT).show()
    }
}