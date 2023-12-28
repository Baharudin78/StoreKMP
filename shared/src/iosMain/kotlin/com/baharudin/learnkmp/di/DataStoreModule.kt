package com.baharudin.learnkmp.di

import com.baharudin.learnkmp.data.repository.dataStoreFileName
import com.baharudin.learnkmp.data.repository.getDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun getDataStoreModuleByPlatform() = module {
    single {
        getDataStore {
            val documentDirectory : NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )
            requireNotNull(documentDirectory).path + "/$dataStoreFileName"
        }
    }
    single {  }
}