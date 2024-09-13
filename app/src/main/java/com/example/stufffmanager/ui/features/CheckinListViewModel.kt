package com.example.stufffmanager.ui.features

import androidx.lifecycle.ViewModel
import com.example.staffmanager.logic.network.StuffNetwork
import com.example.stufffmanager.logic.model.Attendance
import com.example.stufffmanager.logic.model.CheckinResponse

class CheckinListViewModel : ViewModel() {

    val checkinList = ArrayList<Attendance>()



}