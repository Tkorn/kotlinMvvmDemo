package com.fyt.myapplication.base.globalsetting

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

class GlobalCoroutineExceptionHandler: CoroutineExceptionHandler {

    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}