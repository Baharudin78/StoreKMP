package com.baharudin.learnkmp.android

import android.app.Application
import com.baharudin.learnkmp.di.appModule
import com.baharudin.learnkmp.di.getDataStoreModuleByPlatform
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(appModule, getDataStoreModuleByPlatform())
        }
    }
}