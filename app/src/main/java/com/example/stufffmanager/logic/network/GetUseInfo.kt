package com.example.staffmanager.logic.network

import com.example.staffmanager.logic.model.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetUseInfo {
    @GET("/staff/user/info")
    fun getUserInfo(@Query("token")token : String) : Call<UserInfoResponse>
}