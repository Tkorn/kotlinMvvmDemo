package com.fyt.mvvm.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.fyt.mvvm.common.AppManager

abstract class BaseActivity<VM: BaseViewModel<out BaseRepository, out BaseUiState>> : AppCompatActivity() , IActivity {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initView(savedInstanceState))
        mViewModel = initViewModel()
        initData(savedInstanceState)
        var isNotAdd = false
        if (intent != null){
            isNotAdd = intent.getBooleanExtra(AppManager.IS_NOT_ADD_ACTIVITY_LIST, false)
        }
        if (!isNotAdd){
            AppManager.getAppManager()!!.addActivity(this)
        }


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

    override fun onDestroy() {
        super.onDestroy()
        AppManager.getAppManager()!!.removeActivity(this);
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

}