package com.fyt.myapplication.mvvm.adapter

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.fyt.mvvm.adapter.BasePagingAdapter
import com.fyt.myapplication.R
import com.fyt.myapplication.base.adapter.BaseRvHolder
import com.fyt.myapplication.mvvm.repository.bean.UserBean
import kotlinx.android.synthetic.main.recycle_list.view.*


class UserPageListAdapter: BasePagingAdapter<UserBean>(DIFF_CALLBACK){
    companion object{
        private val DIFF_CALLBACK
                = object : DiffUtil.ItemCallback<UserBean>(){
            override fun areItemsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserBean, newItem: UserBean): Boolean {
                return oldItem == newItem
            }
        }

    }

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
