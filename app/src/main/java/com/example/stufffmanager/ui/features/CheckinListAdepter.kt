package com.example.stufffmanager.ui.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stufffmanager.R
import com.example.stufffmanager.logic.model.Attendance

class CheckinListAdepter(private val fragment: CheckinListActivity, private val CheckinList: List<Attendance>) :
    RecyclerView.Adapter<CheckinListAdepter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val attendanceName: TextView = view.findViewById(R.id.attendanceName)
        val attendanceDepartment: TextView = view.findViewById(R.id.attendanceDepartment)
        val attendanceTime: TextView = view.findViewById(R.id.attendanceTime)
        val attendanceTitle : TextView =view.findViewById(R.id.attendanceTitle)
        val attendanceProfile: TextView = view.findViewById(R.id.attendanceProfile)
        val attendanceStatus : ImageView =view.findViewById(R.id.attendanceStatusImg)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.checkinlist_item,
            parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attendance = CheckinList[position]
        holder.attendanceName.text = attendance.clerkId
        holder.attendanceDepartment.text = attendance.departmentId
        holder.attendanceTime.text = attendance.time
        holder.attendanceTitle.text = attendance.name
        when(attendance.status){
            "0"->holder.attendanceProfile.text = "正常"
            "1"->holder.attendanceProfile.text = "事假"
            "2"->holder.attendanceProfile.text = "迟到"
            "3"->holder.attendanceProfile.text = "早退"
            "4"->holder.attendanceProfile.text = "病假"
            "5"->holder.attendanceProfile.text = "旷工"
            "6"->holder.attendanceProfile.text = "休补"
        }
        when(attendance.audit){
            "1"->holder.attendanceStatus.setImageResource(R.drawable.ok_128sp)
            "2"->holder.attendanceStatus.setImageResource(R.drawable.no_128sp)
        }


    }
    override fun getItemCount() = CheckinList.size
}
