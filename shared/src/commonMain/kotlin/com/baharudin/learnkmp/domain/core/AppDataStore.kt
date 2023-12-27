package com.baharudin.learnkmp.domain.core

interface AppDataStore {
    suspend fun setValue(key: String, value:String)
    suspend fun getValue(key: String):String?
}