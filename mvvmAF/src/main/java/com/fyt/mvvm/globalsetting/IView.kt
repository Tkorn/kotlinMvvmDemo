package com.fyt.mvvm.globalsetting

import android.content.Context

interface IView {
    /**
     * 显示加载
     */
    fun showLoading(context: Context)

    /**
     * 隐藏加载
     */
    fun hideLoading()

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 `null`
     */
    fun showMessage(context: Context, message: String)

}