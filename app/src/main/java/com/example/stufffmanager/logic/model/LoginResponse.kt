package com.example.staffmanager.logic.model

data class LoginResponse(val success:Boolean,val code:Int,val message:String,val data:Data)
{
    data class Data(val token:String)
}


