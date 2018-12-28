package com.amitgupta.android_mvvm_kotlin_example.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import java.net.SocketTimeoutException

abstract class BaseFragment : Fragment() {

    abstract val layoutResId: Int
    lateinit var baseActivityContext: Context
    lateinit var baseFragmentListener: BaseFragmentListener

    var isFragmentVisibleToUser: Boolean = false

    var title = javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivityContext = context
        if (context is BaseFragmentListener) {
            baseFragmentListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BaseFragmentListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutResId, container, false)
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) =
        baseFragmentListener.replaceFragment(fragment, addToBackStack)

    fun addFragment(fragment: Fragment, addToBackStack: Boolean = false) =
        baseFragmentListener.addFragment(fragment)

    //Implemented by BaseActivity for replacing and adding new fragments
    interface BaseFragmentListener {
        fun addFragment(fragment: Fragment)
        fun replaceFragment(fragment: Fragment, addToBackStack: Boolean)

        fun clearDataAndRedirectToLogin()
        fun successHandler(code: Int, message: String, successFunc: () -> Unit)
        fun errorHandler(exception: Throwable?)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        isFragmentVisibleToUser = isVisibleToUser
    }

//    inline fun successHandler(code: String, message: String, successFunc: () -> Unit) {
//        when (code) {
//            "0000" -> successFunc()
//            "0002" -> toast(message)
//            "0003" -> baseFragmentListener.clearDataAndRedirectToLogin()
//        }
//    }

    fun successHandler(code: Int = -1, message: String, successFunc: () -> Unit) {
        baseFragmentListener.successHandler(code, message, successFunc)
    }


    fun errorHandler(exception: Throwable?) {
        baseFragmentListener.errorHandler(exception)
    }
}