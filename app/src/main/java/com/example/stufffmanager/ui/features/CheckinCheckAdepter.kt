package com.example.stufffmanager.ui.features

import android.net.Network
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.staffmanager.logic.network.StuffNetwork
import com.example.stufffmanager.R
import com.example.stufffmanager.StufffManageApplication
import com.example.stufffmanager.logic.model.Attendance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CheckinCheckAdepter(private val fragment: CheckinCheckActivity, private val CheckinList: List<Attendance>,
                          private val lifecycleScope: CoroutineScope) :
    RecyclerView.Adapter<CheckinCheckAdepter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val attendanceName: TextView = view.findViewById(R.id.attendanceName)
        val attendanceDepartment: TextView = view.findViewById(R.id.attendanceDepartment)
        val attendanceTime: TextView = view.findViewById(R.id.attendanceTime)
        val attendanceTitle : TextView =view.findViewById(R.id.attendanceTitle)
        val attendanceProfile: TextView = view.findViewById(R.id.attendanceProfile)
        val attendanceStatus : ImageView =view.findViewById(R.id.attendanceStatusImg)
        val attendancePassBtn : Button = view.findViewById(R.id.attendancePassBtn)
        val attendanceRefuseBtn :Button = view.findViewById(R.id.attendanceRefuseBtn)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.checkincheck_item,
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
        holder.attendancePassBtn.setOnClickListener{
            val scope = lifecycleScope
            scope.launch {
                val deferredResult = async{
                    StuffNetwork.passAttendance(attendance.id)
                }

                val result = deferredResult.await()
                if(result.success){
                    Toast.makeText(StufffManageApplication.context, "审批成功", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(StufffManageApplication.context, "网络错误", Toast.LENGTH_SHORT).show()
                }

            }
        }
        holder.attendanceRefuseBtn.setOnClickListener{
            val scope = lifecycleScope
            scope.launch {
                val deferredResult = async{
                    StuffNetwork.refussAttendance(attendance.id)
                }
                val result = deferredResult.await()
                if(result.success){
                    Toast.makeText(StufffManageApplication.context, "审批成功", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(StufffManageApplication.context, "网络错误", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }
    override fun getItemCount() = CheckinList.size
}
