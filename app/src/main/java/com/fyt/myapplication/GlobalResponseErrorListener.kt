package com.fyt.myapplication

import android.content.Context
import android.net.ParseException
import android.widget.Toast
import com.fyt.mvvm.globalsetting.IResponseErrorListener
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class GlobalResponseErrorListener(context: Context): IResponseErrorListener{

    var mContext = context

    override fun handleResponseError(t: Throwable) {
        Timber.tag("Catch-Error").w(t.message)
        //这里不光只能打印错误, 还可以根据不同的错误做出不同的逻辑处理
        //这里只是对几个常用错误进行简单的处理, 展示这个类的用法, 在实际开发中请您自行对更多错误进行更严谨的处理
        var msg = "未知错误"
        if (t is UnknownHostException) {
            msg = "网络不可用"
        } else if (t is SocketTimeoutException) {
            msg = "请求网络超时"
        } else if (t is HttpException) {
            msg = convertStatusCode(t)
        } else if (t is JsonParseException || t is ParseException
            || t is JSONException || t is JsonIOException) {
            msg = "数据解析错误"
        }
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show()
    }


    private fun convertStatusCode(httpException: HttpException): String {
        return when {
            httpException.code() == 500 -> "服务器发生错误"
            httpException.code() == 404 -> "请求地址不存在"
            httpException.code() == 403 -> "请求被服务器拒绝"
            httpException.code() == 307 -> "请求被重定向到其他页面"
            else -> httpException.message()
        }
    }
}