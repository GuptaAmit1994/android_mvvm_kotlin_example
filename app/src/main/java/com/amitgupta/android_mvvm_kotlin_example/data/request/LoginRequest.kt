package com.amitgupta.android_mvvm_kotlin_example.data.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("email") var email: String = "",
        @SerializedName("password") var password: String="",
        @SerializedName("device_id") var deviceId: String="",
        @SerializedName("device_type") var deviceType: String="",
        @SerializedName("push_notification_token") var pushNotificationToken: String=""
){
        //For test
        val testEmail = "a@a.com"
        val testPassword = "1234567"
}