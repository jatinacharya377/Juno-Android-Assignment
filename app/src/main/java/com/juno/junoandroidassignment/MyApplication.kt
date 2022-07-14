package com.juno.junoandroidassignment

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: MyApplication
            private set
    }
}