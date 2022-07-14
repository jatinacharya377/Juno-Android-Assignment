package com.juno.junoandroidassignment.data.remote.repository

import com.juno.junoandroidassignment.data.remote.api.ApiService

class CryptoRepository {
    private val api = ApiService().getApi()

    suspend fun getEmptyStateCryptoResponse() = api.getEmptyStateCryptoResponse()

    suspend fun getValuesStateCryptoResponse() = api.getValuesStateCryptoResponse()
}