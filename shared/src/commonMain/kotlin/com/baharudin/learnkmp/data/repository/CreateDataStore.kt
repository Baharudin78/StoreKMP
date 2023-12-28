package com.baharudin.learnkmp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import okio.Path.Companion.toPath


private lateinit var dataStore : DataStore<Preferences>
private val lock = SynchronizedObject()

fun getDataStore(productPath : () -> String) : DataStore<Preferences> =
    synchronized(lock){
        if (::dataStore.isInitialized){
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { productPath().toPath()})
                .also { dataStore  = it }
        }
    }
internal const val dataStoreFileName = "com.baharudin.learnkmp.preferences"