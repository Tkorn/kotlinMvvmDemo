package com.fyt.mvvm.base

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fyt.mvvm.common.ClassUtil
import com.fyt.mvvm.globalsetting.IView
import javax.inject.Inject

abstract class BaseVmFragment<VM: BaseViewModel<out BaseRepository>>: Fragment(){

    lateinit var mViewModel: VM
    @Inject
    lateinit var iView: IView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = initDataBinding(inflater,container,savedInstanceState)
        if (rootView == null){
            rootView = inflater.inflate(layoutId(),container,false)
        }
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ClassUtil.getClassType(this,0))

        initData(savedInstanceState)
        observer(mViewModel.showLoading){
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        }

        observer(mViewModel.showToastMsg){
            if (!TextUtils.isEmpty(it)){
                showMessage(it)
            }
        }

    }


    fun <T> LifecycleOwner.observer(liveData: LiveData<T>, observer: (t: T) -> Unit) {
        liveData.observe(this, Observer { it?.let { t -> observer(t) } })
    }



    open fun initDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = null

    open fun showLoading() {
        iView.showLoading(requireContext())
    }

    open fun hideLoading() {
        iView.hideLoading()
    }

    open fun showMessage(message: String){
        iView.showMessage(requireContext(),message)
    }



    abstract fun layoutId():Int


    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    abstract fun initData(savedInstanceState: Bundle?)
}