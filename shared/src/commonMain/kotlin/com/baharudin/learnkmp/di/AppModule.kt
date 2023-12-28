package com.baharudin.learnkmp.di

import com.baharudin.learnkmp.core.platformModule
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(platformModule(), getDataStoreModuleByPlatform(), appModule)
}

fun initKoin() = initKoin {}

val appModule = module {
    single { createKtorClient() }
}

private const val TIME_OUT = 60_00

fun createKtorClient(): HttpClient {
    return HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("HTTP Logging ; " + message)
                }
            }
            level = LogLevel.BODY
        }
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP Status => ${response.status.value}")
            }
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}