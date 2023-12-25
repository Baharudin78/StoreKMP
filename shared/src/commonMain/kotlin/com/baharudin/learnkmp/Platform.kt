package com.baharudin.learnkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform