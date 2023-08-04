package com.hellopharmacy.utilities.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created on *** on 25/10/19.
 */
abstract class RetryableCallback<T>(private val call: Call<T>, totalRetries: Int) :
    Callback<T> {
    private var totalRetries = 3
    private var retryCount = 0
    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        if (!isCallSuccess(response)) if (retryCount++ < totalRetries) {
            Timber.e("$TAG Retrying API Call -  ($retryCount / $totalRetries)")
            retry()
        } else onFinalResponse(call, response) else onFinalResponse(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        Timber.e(TAG + " " + t.message)
        if (retryCount++ < totalRetries) {
            Timber.e("$TAG Failure Retrying API Call -  ($retryCount / $totalRetries)")
            retry()
        } else onFinalFailure(call, t)
    }

    open fun onFinalResponse(
        call: Call<T>?,
        response: Response<T>?
    ) {
    }

    open fun onFinalFailure(call: Call<T>?, t: Throwable?) {}
    private fun retry() {
        call.clone().enqueue(this)
    }

    private fun isCallSuccess(response: Response<*>): Boolean {
        val code = response.code()
        return code >= 200 && code < 400
    }

    companion object {
        private val TAG = RetryableCallback::class.java.simpleName
    }

    init {
        this.totalRetries = totalRetries
    }
}