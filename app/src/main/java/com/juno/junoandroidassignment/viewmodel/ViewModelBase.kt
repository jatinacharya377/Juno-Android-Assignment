package com.juno.junoandroidassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juno.junoandroidassignment.data.model.crypto.CryptoState
import com.juno.junoandroidassignment.data.model.error.ErrorCallback
import com.juno.junoandroidassignment.data.model.response.CryptoStateResponse
import com.juno.junoandroidassignment.utils.NetworkUtils
import kotlinx.coroutines.*

abstract class ViewModelBase(application: Application): AndroidViewModel(application) {
    init {
        onCreate()
    }
    val error = MutableLiveData<ErrorCallback>()
    private val exceptionHandler = CoroutineExceptionHandler {_, throwable ->
        error.value = ErrorCallback(true, NetworkUtils.exceptionHandler(throwable))
    }

    fun getCryptoStateList(response: CryptoStateResponse, state: Int): ArrayList<CryptoState> {
        val cryptoStateList = ArrayList<CryptoState>()
        if (response.cryptoBalance != null) {
            cryptoStateList.add(
                CryptoState(
                    Pair(
                        if (state == 0) "CRYPTO_BALANCE_EMPTY_STATE" else "CRYPTO_BALANCE_VALUES_STATE",
                        response.cryptoBalance
                    )
                )
            )
        }
        if (response.cryptoHoldings?.isNotEmpty() == true) {
            cryptoStateList.add(
                CryptoState(
                    Pair(
                        if (state == 0) "CRYPTO_HOLDINGS_EMPTY_STATE" else "CRYPTO_HOLDINGS_VALUES_STATE",
                        response.cryptoHoldings
                    )
                )
            )
        }
        if (response.allTransactions?.isNotEmpty() == true) {
            cryptoStateList.add(
                CryptoState(
                    Pair(
                        "RECENT_TRANSACTIONS",
                        response.allTransactions
                    )
                )
            )
        }
        if (response.cryptoPrices?.isNotEmpty() == true) {
            cryptoStateList.add(
                CryptoState(
                    Pair(
                        "CRYPTO_PRICES",
                        response.cryptoPrices
                    )
                )
            )
        }
        return cryptoStateList
    }

    fun launchCoroutineScope(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler, CoroutineStart.DEFAULT, block)
    }

    abstract fun onCreate()
}