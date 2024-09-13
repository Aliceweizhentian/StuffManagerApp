package com.example.staffmanager.logic.network

import com.example.staffmanager.logic.model.LoginRequest
import com.example.staffmanager.logic.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginService {
    @POST("/staff/user/login")
    fun login(@Body loginrequest: LoginRequest) : Call<LoginResponse>
}