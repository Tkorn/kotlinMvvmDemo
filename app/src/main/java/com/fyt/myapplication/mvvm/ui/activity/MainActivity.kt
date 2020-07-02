package com.fyt.myapplication.mvvm.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.fyt.mvvm.adapter.BasePagingAdapter
import com.fyt.mvvm.base.BaseActivity
import com.fyt.myapplication.R
import com.fyt.myapplication.mvvm.adapter.UserPageListAdapter
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import com.fyt.myapplication.mvvm.viewmodel.MainViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel>(), OnRefreshListener {
    override fun initViewModel(): MainViewModel = getViewModel()

    override fun initView(savedInstanceState: Bundle?): Int = R.layout.activity_main

    val mUserAdapter = UserPageListAdapter()//UserAdapter(mData)

    override fun initData(savedInstanceState: Bundle?) {
        initRecyclerView()

        refreshLayout.setOnRefreshListener(this)

        recyclerView.adapter = mUserAdapter

        mUserAdapter.mOnItemClickListener = object: BasePagingAdapter.OnRecyclerViewItemClickListener<UserBean> {
            override fun onItemClick(view: View, viewType: Int, data: UserBean, position: Int) {
                Toast.makeText(this@MainActivity,data.login,Toast.LENGTH_SHORT).show()
                data.login = "like"
                mUserAdapter.notifyItemChanged(position)
            }
        }

        observe(mViewModel.userLiveData){
            mUserAdapter.submitList(it)
        }


        observe(mViewModel.mUiState){state->
            if (state.referish){
                refreshLayout.finishRefresh(state.referish)
            }
            if(state.loadMode){
                refreshLayout.finishLoadMore(state.loadMode)
            }
        }

        refreshLayout.autoRefreshAnimationOnly()
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.onRefresh()
    }


}
