package com.baharudin.learnkmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    val count : Int,
    val rate : Double
)
