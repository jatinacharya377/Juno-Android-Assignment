package com.juno.junoandroidassignment.data.model.crypto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllTransactions(
    val txn_logo: String?,
    val title: String?,
    val txn_time: String?,
    val txn_amount: String?,
    val txn_sub_amount: String
): Parcelable
