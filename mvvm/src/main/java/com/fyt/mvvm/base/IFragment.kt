package com.fyt.mvvm.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface IFragment {

    /**
     * 初始化 View, 如果 [.initView] 返回 0, 框架则不会调用 [Activity.setContentView]
     *
     * @param savedInstanceState
     * @return
     */
    fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    /**
     * 初始化数据
     *
     * @param savedInstanceState
     */
    fun initData(savedInstanceState: Bundle?)

    /**
     * 显示加载
     */
    fun showLoading() {

    }

    /**
     * 隐藏加载
     */
    fun hideLoading() {

    }

    /**
     * 显示信息
     *
     * @param message 消息内容, 不能为 `null`
     */
    fun showMessage(message: String)


}