package com.juno.junoandroidassignment.data.remote.api

import com.juno.junoandroidassignment.data.model.response.CryptoStateResponse
import retrofit2.http.GET

interface CryptoApi {
    @GET("empty-home")
    suspend fun getEmptyStateCryptoResponse(): CryptoStateResponse

    @GET("home")
    suspend fun getValuesStateCryptoResponse(): CryptoStateResponse
}