package com.example.stufffmanager.logic.network

import com.example.stufffmanager.logic.model.CheckAttendanceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface PassAttendance {
    @PUT("/staff/attendance/updateAttendanceOk/{id}")
    fun passAttendance(@Path("id")id : String) : Call<CheckAttendanceResponse>
}