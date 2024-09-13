package com.example.stufffmanager.logic.model


data class CheckinResponse(val success : Boolean,val code : Int,val message : String,val data : Data)
{
    data class Data(val attendanceList : List<Attendance>)
}

data class Attendance(val id : String,val name :String,val status : String,val time : String,
                      val audit : String,val clerkId : String,val departmentId : String,
                      val isDelete : Int, val gmtCreator: String,val gmtModified : String)
