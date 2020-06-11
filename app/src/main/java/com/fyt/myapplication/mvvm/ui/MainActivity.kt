package com.fyt.myapplication.mvvm.ui

import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.fyt.mvvm.base.BaseActivity
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.adapter.UserAdapter
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.myapplication.mvvm.viewmodel.MainViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel>(), OnRefreshListener, OnRefreshLoadMoreListener {
    override fun initViewModel(): MainViewModel = getViewModel()

    override fun initView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    val mData: MutableList<UserBean> = mutableListOf()
    val mUserAdapter = UserAdapter(mData)

    override fun initData(savedInstanceState: Bundle?) {
        initRecyclerView()

        refreshLayout.setOnRefreshListener(this)
        refreshLayout.setOnRefreshLoadMoreListener(this)

        recyclerView.adapter = mUserAdapter

        observe(mViewModel.mUiState){state->
//            Timber.e(Gson().toJson(state))
            if (state.referish){
                if (state.userList.size>0){
                    mData.clear()
                    mData.addAll(state.userList)
                    mUserAdapter.notifyDataSetChanged()
                }
                refreshLayout.finishRefresh(state.referish)
            }
            if(state.loadMode){
                if (state.userList.size>0){
                    mData.addAll(state.userList)
                    mUserAdapter.notifyDataSetChanged()
                }
                refreshLayout.finishLoadMore(state.loadMode)
            }
        }

        refreshLayout.autoRefresh()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.onRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mViewModel.onLoadMore()
    }


}
