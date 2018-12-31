package com.amitgupta.android_mvvm_kotlin_example.data.response

import com.amitgupta.android_mvvm_kotlin_example.base.BaseApiResponse
import com.google.gson.annotations.SerializedName


data class LoginResponse(
        @SerializedName("data") var loginData: LoginData? = null
) : BaseApiResponse()

data class LoginData(

        @SerializedName("auth_token") var authToken: String = "",
        @SerializedName("id") var userId: Int = 0,
        @SerializedName("first_name") var firstName: String = "",
        @SerializedName("last_name") var lastName: String = "",
        @SerializedName("profile_picture") var profilePicture: String = "",
        @SerializedName("device_id") var deviceId: String="",
        @SerializedName("device_type") var deviceType: Int = 0,
        @SerializedName("push_notification_token") var pushNotificationToken: String = "",
        @SerializedName("is_first_login") var isFirstLogin: Boolean = true,

        //Self created property
        /* TODO = Remove it when autao login api is implemented.For now, the auto login is done using username and password using the logina api. In Future it will be done using auth token  with autologin api
         */
        var password: String = "",
        var userEmail: String = ""
)


