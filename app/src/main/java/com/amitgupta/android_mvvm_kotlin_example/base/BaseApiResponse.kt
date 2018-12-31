package com.amitgupta.android_mvvm_kotlin_example.base

import com.google.gson.annotations.SerializedName

abstract class BaseApiResponse {
    @SerializedName("code") var code: Int = -1
    @SerializedName("message") var message: String = ""

}