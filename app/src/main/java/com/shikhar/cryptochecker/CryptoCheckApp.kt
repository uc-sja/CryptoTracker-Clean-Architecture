package com.shikhar.cryptochecker

import android.app.Application
import com.shikhar.cryptochecker.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoCheckApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoCheckApp)
            androidLogger()

            modules(appModule)
        }
    }
}