package com.baharudin.learnkmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val access_token : String? = null,
    val refresh_token : String? = null
) : BaseResponse()
