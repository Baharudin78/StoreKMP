package com.baharudin.learnkmp.data.model

data class ProductDto(
    val categoryDto: CategoryDto,
    val creationAt : String,
    val description : String,
    val id : Int,
    val images : List<String>,
    val price : Float,
    val title : String,
    val updatedAt : String,
    val rating : RatingDto?
)
