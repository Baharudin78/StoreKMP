package com.baharudin.learnkmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterModel(
    val email: String,
    val password: String,
    val name: String,
    val avatar: String
)
