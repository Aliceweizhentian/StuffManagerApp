package com.example.staffmanager.logic.model



data class AnnoucementResponse(val success : Boolean,val code : Int,val message : String,
                                val data: Data)
{
    data class Data(val announcementList: List<Announcement>)
}



data class Announcement(val id : String,val title : String,val content : String,val time : String,
                        val isDelete : Int,val gmtCreate : String,val gmtModified : String)
