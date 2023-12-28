package com.baharudin.learnkmp.data.core

import com.baharudin.learnkmp.core.Context
import com.baharudin.learnkmp.core.getData
import com.baharudin.learnkmp.core.putData
import com.baharudin.learnkmp.domain.core.AppDataStore

const val APP_DATASTORE = "com.baharudin.learnkmp"
class AppDataStoreManagement(val context : Context) :AppDataStore{
    override suspend fun setValue(key: String, value: String) {
        context.putData(key, value)
    }

    override suspend fun getValue(key: String): String? {
        return context.getData(key)
    }
}