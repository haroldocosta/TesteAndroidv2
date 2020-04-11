package com.haroldocosta.bankapp.loginScreen

import android.util.Log
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.TimeUnit


interface LoginPresenterInput {
    fun presentLoginMetaData(response: LoginResponse?)
}

class LoginPresenter : LoginPresenterInput {
    var output: WeakReference<LoginActivityInput>? = null

    var TAG: String = LoginPresenter::class.java.simpleName

    override fun presentLoginMetaData(response: LoginResponse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
