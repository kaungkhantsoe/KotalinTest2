package com.example.yammobotsserver.kotalintest2

import android.app.Application
import com.example.yammobotsserver.kotalintest2.data.model.PhoneDataModel

class RootApp : Application() {

    override fun onCreate() {
        super.onCreate()

        PhoneDataModel.initInstance(this)
    }
}