package com.amitgupta.android_mvvm_kotlin_example.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amitgupta.android_mvvm_kotlin_example.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    var userName = ""
    var password = ""

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
                //mvvm implementation for login
            }
        }
    }
}