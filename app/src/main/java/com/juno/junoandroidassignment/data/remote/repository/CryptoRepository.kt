package com.juno.junoandroidassignment.data.remote.repository

import com.juno.junoandroidassignment.data.remote.api.ApiService

/**
 * This component is the bridge between our API interface and ViewModel.
 * It is responsible for making API calls.
 * @author: Jagannath Acharya
 */
class CryptoRepository {
    private val api = ApiService().getApi()

    suspend fun getEmptyStateCryptoResponse() = api.getEmptyStateCryptoResponse()

    suspend fun getValuesStateCryptoResponse() = api.getValuesStateCryptoResponse()
}