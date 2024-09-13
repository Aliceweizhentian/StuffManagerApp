package com.example.staffmanager.logic.network

import android.util.Log
import com.example.staffmanager.logic.model.LoginRequest
import com.example.staffmanager.logic.model.UserInfoResponse
import com.example.stufffmanager.logic.model.CheckAttendanceResponse
import com.example.stufffmanager.logic.model.CheckinResponse
import com.example.stufffmanager.logic.network.GetCheckinListService
import com.example.stufffmanager.logic.network.PassAttendance
import com.example.stufffmanager.logic.network.RefussAttendance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object StuffNetwork {
    private val loginService = ServiceCreate.create(LoginService::class.java)

    private val getCheckinListService = ServiceCreate.create(GetCheckinListService::class.java)

    private val getAnnouncementService = ServiceCreate.create(GetAnnouncementService::class.java)

    private val getUserInfoService = ServiceCreate.create(GetUseInfo::class.java)

    private val passAttendanceService = ServiceCreate.create(PassAttendance::class.java)

    private val refussAttendanceService = ServiceCreate.create(RefussAttendance::class.java)

    suspend fun getAnnouncement() = getAnnouncementService.getAnnouncement().awit()

    suspend fun login(loginRequest: LoginRequest) = loginService.login(loginRequest).awit()

    suspend fun refussAttendance(id : String) : CheckAttendanceResponse{
        return try{
            refussAttendanceService.refussAttendance(id).awit()
        }
        catch (e:Exception){
            Log.d("cici","here is refuseAttendance error is $e")
            throw e
        }
    }

    suspend fun passAttendance(id : String) : CheckAttendanceResponse{
        return try{
            passAttendanceService.passAttendance(id).awit()
        }
        catch (e:Exception){
            Log.d("cici","here is passAttendance error is $e")
            throw e
        }
    }

    suspend fun getUserInfo(token:String) : UserInfoResponse{
        return try{
            getUserInfoService.getUserInfo(token).awit()
        }
        catch (e:Exception){
            Log.d("cici","here is getuserInfo error is $e")
            throw e
        }
    }

    suspend fun getCheckinList() : CheckinResponse{
        return try{
            getCheckinListService.getCheckinList().awit()
        }catch (e:Exception){
            Log.d("cici","get checkinList error is $e")
            throw e
        }
    }



    private suspend fun <T> Call<T>.awit() : T {
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body != null){
                        continuation.resume(body)
                    }
                    else
                        Log.d("cici","here is call.awit requestBody is null")
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.d("cici","here is call.awit 请求失败,因为$t")
                }
            })

        }
    }

}