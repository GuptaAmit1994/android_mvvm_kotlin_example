package com.amitgupta.android_mvvm_kotlin_example.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import com.amitgupta.android_mvvm_kotlin_example.Constants
import com.amitgupta.android_mvvm_kotlin_example.R


abstract class BaseActivity : AppCompatActivity(), BaseFragment.BaseFragmentListener {
    abstract val layoutResId: Int

    lateinit var mandatoryUpdateAlertDialog: AlertDialog
    lateinit var optionalUpdateAlertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
    }

    fun initialFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


    override fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        var fragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)


        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commit()
        }
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment)
            //TODO add a parameter(trasition:Boolen,fragmentTransitionId:int) in this fucntion so that required transition can be applied
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .commit()
    }

    override fun clearDataAndRedirectToLogin() {
        //TODO Clear Used Data and Others if required
    }

    override fun successHandler(code: Int, message: String, successFunc: () -> Unit) {
        when (code) {
            Constants.API_FAILURE_CODE -> toast("$message")
            Constants.API_SUCCESS_CODE -> successFunc()
            Constants.API_INVALID_AUTH_CODE -> clearDataAndRedirectToLogin()
            Constants.API_APP_MANDATORY_AUTH_CODE -> successFunc()
            Constants.API_APP_OPTIONAL_UPDATE -> successFunc()

        }
    }



    override fun errorHandler(exception: Throwable?) {
        when {
//            debugTest -> toast("Error: " + exception?.localizedMessage)
            exception is SocketTimeoutException -> toast(getString(R.string.error_network_connection_timeout))
            exception is JsonSyntaxException -> toast(getString((R.string.error_data_could_not_ne_parsed)))
            exception is UnknownHostException -> toast(getString(R.string.no_internet_connection_msg))
            else -> {
                toast(getString(R.string.error_network_unknown))
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (::mandatoryUpdateAlertDialog.isInitialized) {
            mandatoryUpdateAlertDialog.cancel()
        }
        if (::optionalUpdateAlertDialog.isInitialized) {
            optionalUpdateAlertDialog.cancel()
        }
    }
}