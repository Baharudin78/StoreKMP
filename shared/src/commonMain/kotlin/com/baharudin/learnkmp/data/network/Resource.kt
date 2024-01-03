package com.baharudin.learnkmp.data.network

sealed class Resource<out R> {
    data class Success<out R>(val result : R) : Resource<R>()
    data class Failure<out R>(val exception: Exception) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}