package com.baharudin.learnkmp.di

import com.baharudin.learnkmp.data.repository.dataStoreFileName
import com.baharudin.learnkmp.data.repository.getDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun getDataStoreModuleByPlatform() = module {
    single {
        getDataStore {
            androidContext().filesDir?.resolve(dataStoreFileName)?.absolutePath
                ?: throw Exception("Could not get android data store context")
        }
    }
    single {  }
}