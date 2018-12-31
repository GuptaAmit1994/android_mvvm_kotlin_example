package com.amitgupta.android_mvvm_kotlin_example.data

class Resource<T> private constructor(val status: Status, val data: T?,val exception: AppException?,val percantage:Int =0) {


    companion object {

        fun <T> success(data: T?): Resource<T> {

            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(exception: AppException?): Resource<T> {
            return Resource(Status.ERROR, null, exception)
        }

        fun <T> loading(data: T?,percantage: Int=0): Resource<T> {
            return Resource(Status.LOADING, data, null,percantage)
        }

    }
}

enum class Status {
    SUCCESS, ERROR, LOADING
}

class AppException(val exception: Throwable?): Exception();