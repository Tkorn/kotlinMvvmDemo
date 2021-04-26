package com.fyt.mvvm.base

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fyt.mvvm.common.ActivityCollector
import com.fyt.mvvm.common.ClassUtil
import com.fyt.mvvm.globalsetting.IView
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseVmActivity<VM: BaseViewModel<out BaseRepository>>: AppCompatActivity() {
    lateinit var mViewModel: VM
    @Inject
    lateinit var iView: IView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!initDataBinding()){
            setContentView(layoutId())
        }
        ActivityCollector.add(WeakReference(this))
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

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.remove(WeakReference(this))
    }


    fun <T> LifecycleOwner.observer(liveData: LiveData<T>, observer: (t: T) -> Unit) {
        liveData.observe(this, Observer { it?.let { t -> observer(t) } })
    }

    open fun initDataBinding():Boolean = false

    open fun showLoading() {
        iView.showLoading(this)
    }

    open fun hideLoading() {
        iView.hideLoading()
    }

    open fun showMessage(message: String){
        iView.showMessage(this,message)
    }


    abstract fun layoutId():Int


    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    abstract fun initData(savedInstanceState: Bundle?)


}