package com.arkvis.orchid

import android.app.Application
import com.arkvis.orchid.common.LocalStorageHelper

open class BaseApp : Application()
{
    companion object {
    var localStorage: LocalStorageHelper? = null
    lateinit var instance: BaseApp
        private set
}

    override fun onCreate() {
        super.onCreate()
        instance = this
        localStorage = LocalStorageHelper(applicationContext)
    }
}