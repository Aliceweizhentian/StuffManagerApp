package com.example.stufffmanager.logic.network

import com.example.stufffmanager.logic.model.CheckinResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetCheckinListService {
    @GET("/staff/attendance/list")
    fun getCheckinList() : Call<CheckinResponse>
}