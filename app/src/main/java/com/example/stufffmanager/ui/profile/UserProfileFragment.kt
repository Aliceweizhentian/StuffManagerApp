package com.example.stufffmanager.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.staffmanager.logic.network.StuffNetwork
import com.example.stufffmanager.MainActivity
import com.example.stufffmanager.R
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class UserProfileFragment : Fragment() {

    val loginViewModel by lazy{ ViewModelProvider(activity as MainActivity).get(LoginViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }


    override fun onResume() {
        super.onResume()
        val nameText : TextView = requireView().findViewById(R.id.NameProfileText)
        val token = loginViewModel.token
        val position : TextView = requireView().findViewById(R.id.PrositionProfileText)
        val scope = lifecycleScope
        scope.launch {
            val deferredResult = async{
                StuffNetwork.getUserInfo(token)
            }
            val result = deferredResult.await()
            Log.d("cici","token is $token")
            Log.d("cici","result is ${result.toString()}")
            Log.d("cici","name is ${result.data.name}")
            nameText.text =  result.data.name
            position.text = result.data.introduction
        }

    }



}