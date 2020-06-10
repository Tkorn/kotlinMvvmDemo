package com.fyt.myapplication.mvvm.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.fyt.myapplication.R
import com.fyt.myapplication.base.adapter.BaseRvAdapter
import com.fyt.myapplication.base.adapter.BaseRvHolder
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import kotlinx.android.synthetic.main.recycle_list.view.*

class UserAdapter(userList: List<UserBean>): BaseRvAdapter<UserBean>(userList) {


    override fun getHolder(view: View, viewType: Int): BaseRvHolder<UserBean> {
        return UserHolder(view)
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.recycle_list
    }

    class UserHolder(itemView: View): BaseRvHolder<UserBean>(itemView){
        override fun setData(data: UserBean, position: Int) {
            itemView.tv_name.text = data.login

            Glide.with(itemView.context)
                .load(data.avatar_url)
                .into(itemView.iv_avatar)

        }
    }
}