package com.cerulik.scratchcard

import android.app.Application
import com.cerulik.scratchcard.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ScratchCardApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScratchCardApplication)
            modules(appModule)
        }
    }
}