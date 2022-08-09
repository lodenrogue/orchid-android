package com.arkvis.orchid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import moduleList
import org.koin.core.context.GlobalContext.startKoin

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin { modules(moduleList) }
    }
}