package com.example.stufffmanager.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.staffmanager.logic.model.LoginRequest
import com.example.stufffmanager.MainActivity
import com.example.stufffmanager.R

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_not_login, container, false)
    }


    override fun onResume() {
        super.onResume()

        //变量
        val loginViewModel = ViewModelProvider(activity as MainActivity).get(LoginViewModel::class.java)
        val loginBtn : Button = requireView().findViewById(R.id.login_btn)
        val usernameText : EditText = requireView().findViewById(R.id.userNameEditText)
        val passwordText : EditText = requireView().findViewById(R.id.passwordEditText)

        loginBtn.setOnClickListener {
            val username = usernameText.text.toString()
            val password = passwordText.text.toString()
            val login = LoginRequest(username,password)
            loginViewModel.viewModellogin(login)
            Log.d("cici","登录！")
        }

         fun showFragment(fragment: Fragment) {
            val fragmentTransaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.homeFragment,fragment)
            fragmentTransaction.commit()
        }

        //这个result是response变化之后的最新值
        loginViewModel.response.observe(this, Observer { response->
            val loginResponse = response.getOrNull()
            Log.d("cici","观察到loginResponse is ${loginResponse.toString()}")
            if(loginResponse?.success == true)
            {
                loginViewModel.setLoginFlagTrue()
                loginViewModel.token = loginResponse.data.token
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).apply {
                    setGravity(Gravity.CENTER, 0, 0) // 设置位置为屏幕中央
                    show()
                }
                showFragment(UserProfileFragment())
            }
        })


    }
}