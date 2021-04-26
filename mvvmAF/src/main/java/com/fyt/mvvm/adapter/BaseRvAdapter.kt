package com.fyt.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fyt.myapplication.base.adapter.BaseRvHolder

abstract class BaseRvAdapter<T>(info: List<T>): RecyclerView.Adapter<BaseRvHolder<T>>() {
    lateinit var mHolder: BaseRvHolder<T>
    var mOnItemClickListener: OnRecyclerViewItemClickListener<T>? = null
    var  mInfos = info

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRvHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(viewType), parent, false)
        mHolder = getHolder(view, viewType)
        //设置Item点击事件
        mHolder.setOnItemClickListener(object: BaseRvHolder.OnViewClickListener{
            override fun onViewClick(view: View, position: Int){
                if (mOnItemClickListener!= null && mInfos.isNotEmpty()) {
                    mOnItemClickListener!!.onItemClick(view, viewType, mInfos[position], position)
                }
            }
        })
        return mHolder
    }

    abstract fun getHolder(view: View, viewType: Int): BaseRvHolder<T>

    abstract fun getLayoutId(viewType: Int): Int

    override fun getItemCount(): Int {
        return mInfos.size
    }

    override fun onBindViewHolder(holder: BaseRvHolder<T>, position: Int) {
        holder.setData(mInfos[position], position)
    }

    /**
     * 返回数据集合
     *
     * @return 数据集合
     */
    fun getInfos(): List<T> {
        return mInfos
    }

    /**
     * 获得 RecyclerView 中某个 position 上的 item 数据
     *
     * @param position 在 RecyclerView 中的位置
     * @return 数据
     */
    fun getItem(position: Int): T? {
        return mInfos[position]
    }

    /**
     * 遍历所有 [BaseHolder], 释放他们需要释放的资源
     *
     * @param recyclerView [RecyclerView]
     */
    fun releaseAllHolder(recyclerView: RecyclerView?) {
        if (recyclerView == null) return
        for (i in recyclerView.childCount - 1 downTo 0) {
            val view = recyclerView.getChildAt(i)
            val viewHolder = recyclerView.getChildViewHolder(view)
            if (viewHolder != null && viewHolder is BaseRvHolder<*>) {
                viewHolder.onRelease()
            }
        }
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