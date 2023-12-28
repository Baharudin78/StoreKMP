package com.baharudin.learnkmp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val creationAt : String,
    val id : Int,
    val image : String,
    val name : String,
    val updatedAt : String
)
