package com.example.staffmanager.logic.network

import com.example.staffmanager.logic.model.AnnoucementResponse
import com.example.staffmanager.logic.model.Announcement
import retrofit2.Call
import retrofit2.http.GET

interface GetAnnouncementService {
    @GET("/staff/announcement/list")
    fun getAnnouncement() : Call<AnnoucementResponse>

}