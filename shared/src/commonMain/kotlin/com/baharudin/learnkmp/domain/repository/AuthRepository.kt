package com.baharudin.learnkmp.domain.repository

import com.baharudin.learnkmp.data.model.LoginResponseDto
import com.baharudin.learnkmp.data.model.RegisterModel
import com.baharudin.learnkmp.data.model.RegisterResponseDto
import com.baharudin.learnkmp.data.network.Resource

interface AuthRepository {
    suspend fun login(userName : String, password : String) : Resource<LoginResponseDto>
    suspend fun register(registerModel : RegisterModel) : Resource<RegisterResponseDto>
}