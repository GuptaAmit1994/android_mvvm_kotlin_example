package com.amitgupta.android_mvvm_kotlin_example.data.repository

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amitgupta.android_mvvm_kotlin_example.Constants.API_FAILURE_CODE
import com.amitgupta.android_mvvm_kotlin_example.Constants.API_SUCCESS_CODE
import com.amitgupta.android_mvvm_kotlin_example.data.Resource
import com.amitgupta.android_mvvm_kotlin_example.data.request.LoginRequest
import com.amitgupta.android_mvvm_kotlin_example.data.response.LoginData
import com.amitgupta.android_mvvm_kotlin_example.data.response.LoginResponse
import kotlin.concurrent.thread

object LoginRepository {

    fun getLoginResponse(loginRequest: LoginRequest): LiveData<Resource<LoginResponse>> {

        val responseMutableLiveData = MutableLiveData<Resource<LoginResponse>>()
        responseMutableLiveData.value = Resource.loading(null)

        /**
         * Use Handler/Timer if needed to delay
         */
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (loginRequest.email == loginRequest.testEmail && loginRequest.password == loginRequest.testPassword) {
                val loginData = LoginData(firstName = "TEST", lastName = "test")
                val loginResponse = LoginResponse(loginData)
                loginResponse.code = API_SUCCESS_CODE
                loginResponse.message = "Logged In"
                responseMutableLiveData.value = Resource.success(loginResponse)

            } else {
                val responseError = LoginResponse(null)
                responseError.code = API_FAILURE_CODE
                responseError.message = "Error: email passsword did not match"
                responseMutableLiveData.value = Resource.success(responseError)
            }
        }, 4000)

        return responseMutableLiveData
    }

    fun onPause() {
    }
}
