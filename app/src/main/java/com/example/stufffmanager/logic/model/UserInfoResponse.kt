package com.example.staffmanager.logic.model

data class UserInfoResponse(val success : Boolean,val code : Int,val message : String,val data : Data){
    data class Data(val roles : List<String>,val name : String,val avatar : String,val introduction : String)
}
