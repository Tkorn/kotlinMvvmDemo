package com.fyt.mvvm.common

import android.app.Activity
import android.util.Log
import timber.log.Timber
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

object ActivityCollector {

    private const val TAG = "ActivityCollector"

    private val activityList = ArrayList<WeakReference<Activity>?>()

    fun size(): Int {
        return activityList.size
    }

    fun add(weakRefActivity: WeakReference<Activity>?) {
        activityList.add(weakRefActivity)
    }

    fun remove(weakRefActivity: WeakReference<Activity>?) {
        val result = activityList.remove(weakRefActivity)
        Log.d(TAG, "remove activity reference $result")
    }

    /**
     * 关闭指定的 [Activity] class 的所有的实例
     *
     * @param activityClass
     */
    fun killActivity(activityClass: Class<*>) {
        if (activityList == null) {
            Timber.tag(TAG).w("mActivityList == null when killActivity(Class)")
            return
        }
        synchronized(ActivityCollector::class.java) {
            val iterator = activityList!!.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (next?.get()?.javaClass == activityClass) {
                    iterator.remove()
                    next.get()?.finish()
                }
            }
        }
    }

    fun killAll() {
        if (activityList.isNotEmpty()) {
            for (activityWeakReference in activityList) {
                val activity = activityWeakReference?.get()
                if (activity != null && !activity.isFinishing) {
                    activity.finish()
                }
            }
            activityList.clear()
        }
    }

    /**
     * 关闭所有 [Activity],排除指定的 [Activity]
     *
     * @param excludeActivityName [Activity] 的完整全路径
     */
    fun killAll(vararg excludeActivityName: String) {
        val excludeList = Arrays.asList(*excludeActivityName)
        synchronized(ActivityCollector::class.java) {
            val iterator = activityList.iterator()
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (excludeList.contains(next?.get()?.javaClass?.name))
                    continue
                iterator.remove()
                next?.get()?.finish()
            }
        }
    }



}