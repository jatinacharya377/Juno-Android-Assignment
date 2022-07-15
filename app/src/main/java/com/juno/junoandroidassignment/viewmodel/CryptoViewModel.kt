package com.juno.junoandroidassignment.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.juno.junoandroidassignment.data.model.crypto.CryptoState
import com.juno.junoandroidassignment.data.model.error.ErrorCallback
import com.juno.junoandroidassignment.data.remote.repository.CryptoRepository

class CryptoViewModel(app: Application): ViewModelBase(app) {

    private val repo = CryptoRepository()
    val cryptoState = MutableLiveData<List<CryptoState>>()
    var state = 0

    fun getEmptyStateCryptoResponse() {
        launchCoroutineScope {
            error.postValue(ErrorCallback(false))
            val response = repo.getEmptyStateCryptoResponse()
            val list = getCryptoStateList(response, state)
            cryptoState.postValue(list)
        }
    }

    fun getValuesStateCryptoResponse() {
        launchCoroutineScope {
            error.postValue(ErrorCallback(false))
            val response = repo.getValuesStateCryptoResponse()
            val list = getCryptoStateList(response, state)
            cryptoState.postValue(list)
        }
    }

    override fun onCreate() {}
}