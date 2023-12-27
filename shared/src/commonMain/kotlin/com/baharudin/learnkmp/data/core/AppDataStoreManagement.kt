package com.baharudin.learnkmp.data.core

import com.baharudin.learnkmp.core.Context
import com.baharudin.learnkmp.domain.core.AppDataStore

class AppDataStoreManagement(val context : Context) :AppDataStore{
    override suspend fun setValue(key: String, value: String) {
        context.putD
    }

    override suspend fun getValue(key: String): String? {
        TODO("Not yet implemented")
    }
}