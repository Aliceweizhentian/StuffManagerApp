package com.example.staffmanager.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.staffmanager.logic.model.Announcement
import com.example.stufffmanager.logic.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AnnouncementViewModel : ViewModel() {
    val AnnoucementList = ArrayList<Announcement>()
    val AnnouncementLiveData = MutableLiveData<Int>()

    var name = ""
    var introductin = ""

    val response = AnnouncementLiveData.switchMap { request ->
        Repository.getAnnoucement()
    }

    fun getAnnouncement(){
        AnnouncementLiveData.value = 1
    }

}