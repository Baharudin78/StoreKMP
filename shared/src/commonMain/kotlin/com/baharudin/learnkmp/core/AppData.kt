package com.baharudin.learnkmp.core

expect suspend fun Context.putData(key : String, `object` : String)
expect suspend fun Context.getData(key : String) : String?