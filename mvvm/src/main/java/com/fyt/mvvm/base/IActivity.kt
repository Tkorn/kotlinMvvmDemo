package com.fyt.mvvm.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle

interface IActivity {

    /**
     * 初始化 View, 如果 [.initView] 返回 0, 框架则不会调用 [Activity.setContentView]
     *
     * @param savedInstanceState
     * @return
     */
    fun initView(savedInstanceState: Bundle?): Int

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

    /**
     * 跳转 [Activity]
     *
     * @param intent `intent` 不能为 `null`
     */
    fun launchActivity(intent: Intent)

    /**
     * 杀死自己
     */
    fun killMyself()
}