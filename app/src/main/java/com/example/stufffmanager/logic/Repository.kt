package com.example.stufffmanager.logic


import android.util.Log
import androidx.lifecycle.liveData
import com.example.staffmanager.logic.model.AnnoucementResponse

import com.example.staffmanager.logic.model.LoginRequest
import com.example.staffmanager.logic.model.LoginResponse
import com.example.staffmanager.logic.network.StuffNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object Repository {
    fun login(loginRequest: LoginRequest) = liveData(Dispatchers.IO) {
        val result = try {
            val response = StuffNetwork.login(loginRequest)
            if(response.success)
            {
                Log.d("cici","login请求发送并成功")
                //这个result会返回参数内的值
                Result.success(response)
            } else {
                Log.d("cici","login请求失败 ${response.toString()}")
                Result.failure(RuntimeException("request error is ${response.code}"))
            }
        }
        catch (e:Exception){
            Result.failure(RuntimeException("i dont know why"))
        }
        emit(result)
    }

    fun getAnnoucement() = liveData(Dispatchers.IO) {
        Log.d("cici", "now get announcement")
        val result = try {
            val response = StuffNetwork.getAnnouncement()
            if (response.success) {
                Log.d("cici", "announcement请求成功")
                Result.success(response)
            }
            else{
                Log.d("cici","announcement请求失败")
                Result.failure(RuntimeException("request error is ${response.toString()}"))
            }

        }catch(e : Exception){
            Result.failure(e)
        }
        emit(result)
    }



}
