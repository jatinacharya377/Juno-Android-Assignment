package com.juno.junoandroidassignment.data.model.response

import com.google.gson.annotations.SerializedName
import com.juno.junoandroidassignment.data.model.crypto.AllTransactions
import com.juno.junoandroidassignment.data.model.crypto.CryptoBalance
import com.juno.junoandroidassignment.data.model.crypto.CryptoHoldings
import com.juno.junoandroidassignment.data.model.crypto.CryptoPrices

data class CryptoStateResponse(
    @SerializedName("crypto_balance")
    val cryptoBalance: CryptoBalance?,
    @SerializedName("your_crypto_holdings")
    val cryptoHoldings: List<CryptoHoldings>?,
    @SerializedName("crypto_prices")
    val cryptoPrices: List<CryptoPrices>?,
    @SerializedName("all_transactions")
    val allTransactions: List<AllTransactions>?,
)
