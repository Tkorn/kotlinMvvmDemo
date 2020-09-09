package com.fyt.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fyt.myapplication.base.adapter.BaseRvHolder

abstract class BasePagingAdapter<T : Any>(val callback: DiffUtil.ItemCallback<T>): PagingDataAdapter<T,BaseRvHolder<T>>(callback) {
    lateinit var mHolder: BaseRvHolder<T>


    var mOnItemClickListener: OnRecyclerViewItemClickListener<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRvHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        mHolder = getHolder(view, viewType)
        //设置Item点击事件
        mHolder.setOnItemClickListener(object: BaseRvHolder.OnViewClickListener{
            override fun onViewClick(view: View, position: Int){
                mOnItemClickListener?.let {listener->
                    getItem(position)?.let { listener.onItemClick(view, viewType, it, position) }
                }
            }
        })
        return mHolder
    }

    abstract fun getHolder(view: View, viewType: Int): BaseRvHolder<T>

    abstract fun getLayoutId(viewType: Int): Int

    override fun onBindViewHolder(holder: BaseRvHolder<T>, position: Int) {
        getItem(position)?.let { holder.setData(it, position) }
    }

    /**
     * item 点击事件
     * @param <T>
    </T> */
    interface OnRecyclerViewItemClickListener<T> {

        /**
         * item 被点击
         * @param view 被点击的 [View]
         * @param viewType 布局类型
         * @param data 数据
         * @param position 在 RecyclerView 中的位置
         */
        fun onItemClick(view: View, viewType: Int, data: T, position: Int)
    }

    fun setOnItemClickListener(listener: OnRecyclerViewItemClickListener<T>){
        mOnItemClickListener = listener
    }
}