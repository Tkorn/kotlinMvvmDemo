package com.fyt.mvvm.globalsetting

import android.content.Context
import android.net.ParseException
import android.widget.Toast
import com.google.gson.JsonIOException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import timber.log.Timber
import java.net.SocketTimeoutException
import java.net.UnknownHostException

interface IResponseErrorListener{

    fun handleResponseError(t: Throwable)
}