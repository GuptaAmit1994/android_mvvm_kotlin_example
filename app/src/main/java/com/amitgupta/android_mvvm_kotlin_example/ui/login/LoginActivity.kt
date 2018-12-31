package com.amitgupta.android_mvvm_kotlin_example.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.amitgupta.android_mvvm_kotlin_example.R
import com.amitgupta.android_mvvm_kotlin_example.data.Resource
import com.amitgupta.android_mvvm_kotlin_example.data.Status
import com.amitgupta.android_mvvm_kotlin_example.data.request.LoginRequest
import com.amitgupta.android_mvvm_kotlin_example.data.response.LoginData
import com.amitgupta.android_mvvm_kotlin_example.data.response.LoginResponse
import com.amitgupta.android_mvvm_kotlin_example.ui.dashboard.DashBoardActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    var userName = ""
    var password = ""

    //For Login
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginData: LoginData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        listOf(login).forEach { it.setOnClickListener(this@LoginActivity) }
    }

    override fun onClick(v: View?) {
        when (v) {
            login -> {
                validateAndLogin()
            }
        }
    }

    private fun validateAndLogin() {
        userName = et_user_name.text.toString()
        password = et_password.text.toString()

        when {
            userName.isEmpty() -> {
                et_user_name.error = getString(R.string.error_username_empty)
                et_user_name.requestFocus()
            }
            password.isEmpty() -> {
                et_password.error = getString(R.string.error_password_empty)
                et_password.requestFocus()
            }
            else -> {
                /**
                 * mvvm implementation for login
                 * Check Internet First
                 */
                loginViewModel.getLoginData(LoginRequest(userName, password))
                observeLoginViewModel()
            }
        }
    }
    fun observeLoginViewModel() {
        loginViewModel.getLoginDataObservable().observe(this@LoginActivity, Observer<Resource<LoginResponse>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this@LoginActivity,resource.data?.message,Toast.LENGTH_SHORT).show()
                        //Go to DashBoard
                        startActivity(Intent(this@LoginActivity,DashBoardActivity::class.java))
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(this@LoginActivity,resource.data?.message,Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }

                }
            }

        })
    }
}