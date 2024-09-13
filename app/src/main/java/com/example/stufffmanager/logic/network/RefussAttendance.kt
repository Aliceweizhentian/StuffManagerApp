package com.example.stufffmanager.logic.network

import com.example.stufffmanager.logic.model.CheckAttendanceResponse
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path

interface RefussAttendance {
    @PUT("/staff/attendance/updateAttendanceNo/{id}")
    fun refussAttendance(@Path("id")id : String) : Call<CheckAttendanceResponse>

}