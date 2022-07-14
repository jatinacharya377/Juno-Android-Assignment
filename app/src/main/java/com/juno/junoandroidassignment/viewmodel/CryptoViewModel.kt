package com.juno.junoandroidassignment.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.juno.junoandroidassignment.data.model.error.ErrorCallback
import com.juno.junoandroidassignment.data.model.response.CryptoStateResponse
import com.juno.junoandroidassignment.data.remote.repository.CryptoRepository

class CryptoViewModel(app: Application): ViewModelBase(app) {

    private val repo = CryptoRepository()
    val cryptoResponse = MutableLiveData<CryptoStateResponse>()

    fun getEmptyStateCryptoResponse() {
        launchCoroutineScope {
            val response = repo.getEmptyStateCryptoResponse()
            cryptoResponse.value = response
            error.value = ErrorCallback(false)
        }
    }

    fun getValuesStateCryptoResponse() {
        launchCoroutineScope {
            val response = repo.getValuesStateCryptoResponse()
            cryptoResponse.value = response
            error.value = ErrorCallback(false)
        }
    }

    override fun onCreate() {}
}