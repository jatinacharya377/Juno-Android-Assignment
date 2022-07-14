package com.juno.junoandroidassignment.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object GsonUtils {
    inline fun <reified T> jsonToGson(json: String?): T {
        val gson = GsonBuilder().serializeNulls().create()
        val type: Type = object : TypeToken<T>() {}.type
        return gson.fromJson(
            json,
            type
        )
    }
}