package com.example.stufffmanager.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.staffmanager.logic.model.LoginRequest
import com.example.stufffmanager.logic.Repository
import kotlin.math.log

class LoginViewModel : ViewModel() {

    private var loginFlag = false
    var token = ""
    private var instrodution = ""

    val loginRequestLiveData = MutableLiveData<LoginRequest>()

    //当loginResponse变化时，就会把自己当作参数传入lambda表达式里
    val response = loginRequestLiveData.switchMap { loginRequest->
        Repository.login(loginRequest)
    }

    fun viewModellogin(loginRequest: LoginRequest){
        Log.d("cici","值变了")
        loginRequestLiveData.value = loginRequest
    }

    fun setLoginFlagTrue(){
        Log.d("cici","set flag true ok")
        loginFlag = true
        Log.d("cici","flag is $loginFlag")
    }


    fun getLoginFlag() : Boolean{
        return loginFlag
    }

    override fun onCleared() {
        Log.d("cici","死掉了~")
    }



}
