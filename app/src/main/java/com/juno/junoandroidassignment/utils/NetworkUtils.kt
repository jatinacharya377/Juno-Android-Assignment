package com.juno.junoandroidassignment.utils

import android.util.Log
import com.juno.junoandroidassignment.MyApplication
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.response.GenericResponse
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException

object NetworkUtils {
    fun exceptionHandler(throwable: Throwable): String {
        throwable.message?.let { Log.e("error_message", it) }
        return when (throwable) {
            is HttpException -> {
                val response = throwable.response()?.errorBody()?.string()
                when (throwable.response()?.code()) {
                    400 -> MyApplication.INSTANCE.getString(R.string.bad_request_error)
                    401 -> MyApplication.INSTANCE.getString(R.string.unauthorized_error)
                    403 -> MyApplication.INSTANCE.getString(R.string.forbidden_error)
                    404 -> MyApplication.INSTANCE.getString(R.string.request_not_found_error)
                    500 -> if (!response.isNullOrEmpty()) GsonUtils.jsonToGson<GenericResponse>(
                        response
                    ).message
                        ?: MyApplication.INSTANCE.getString(R.string.something_went_wrong_error)
                    else MyApplication.INSTANCE.getString(R.string.something_went_wrong_error)
                    else -> MyApplication.INSTANCE.getString(R.string.something_went_wrong_error)
                }
            }
            is ConnectException -> MyApplication.INSTANCE.getString(R.string.no_internet_error)
            is SocketTimeoutException -> MyApplication.INSTANCE.getString(R.string.slow_internet_error)
            is TimeoutException -> MyApplication.INSTANCE.getString(R.string.request_took_time_to_respond_error)
            else -> MyApplication.INSTANCE.getString(R.string.something_went_wrong_error)
        }
    }
}