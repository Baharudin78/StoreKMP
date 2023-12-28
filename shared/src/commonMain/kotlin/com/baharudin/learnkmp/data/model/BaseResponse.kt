package com.baharudin.learnkmp.data.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    val statusCode : Int? = null,
    val message : String? = null
)