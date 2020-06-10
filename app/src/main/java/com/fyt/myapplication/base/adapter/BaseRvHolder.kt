package com.fyt.myapplication.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvHolder<T>(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener{
    protected var mOnViewClickListener: OnViewClickListener? = null

    init {
        item.setOnClickListener(this)
    }

    abstract fun setData(data: T, position: Int)

    fun onRelease() {

    }

    override fun onClick(view: View) {
        mOnViewClickListener!!.onViewClick(view,layoutPosition)
    }

    fun setOnItemClickListener(onViewClickListener: OnViewClickListener){
        mOnViewClickListener = onViewClickListener
    }

    /**
     * item 点击事件
     */
    interface OnViewClickListener {

        /**
         * item 被点击
         *
         * @param view     被点击的 [View]
         * @param position 在 RecyclerView 中的位置
         */
        fun onViewClick(view: View, position: Int)
    }

}