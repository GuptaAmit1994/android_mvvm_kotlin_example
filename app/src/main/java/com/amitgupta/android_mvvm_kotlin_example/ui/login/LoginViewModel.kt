package com.amitgupta.android_mvvm_kotlin_example.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.amitgupta.android_mvvm_kotlin_example.data.Resource
import com.amitgupta.android_mvvm_kotlin_example.data.repository.LoginRepository
import com.amitgupta.android_mvvm_kotlin_example.data.request.LoginRequest
import com.amitgupta.android_mvvm_kotlin_example.data.response.LoginResponse

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var loginResponseLiveData : LiveData<Resource<LoginResponse>>

    fun getLoginData(loginRequest: LoginRequest){
        loginResponseLiveData = LoginRepository.getLoginResponse(loginRequest)
    }

    fun getLoginDataObservable():LiveData<Resource<LoginResponse>>{
        return loginResponseLiveData
    }

    fun onPause(){
        /**
         * To stop the network call/data fetching operation call the Respository Pause method.
         */
    }

}