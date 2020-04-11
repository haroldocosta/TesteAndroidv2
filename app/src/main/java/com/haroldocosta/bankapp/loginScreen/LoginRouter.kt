package com.haroldocosta.bankapp.loginScreen

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import androidx.annotation.NonNull
import java.lang.ref.WeakReference
import java.util.*


internal interface LoginRouterInput {
//    fun determineNextScreen(position: Int): Intent?
//    fun passDataToNextScene(position: Int, intent: Intent?)
    fun log()
}

class LoginRouter : LoginRouterInput {
    var activity: WeakReference<LoginActivity>? = null
    
    var TAG: String = LoginRouter::class.java.simpleName

    override fun log(){
        Log.d(TAG, "LoginRouter")
    }
//    @NonNull
//    override fun determineNextScreen(position: Int): Intent { //Based on the position or someother data decide what is the next scene
//        
//    }

//    override fun passDataToNextScene(
//        position: Int,
//        intent: Intent
//    ) { //Based on the position or someother data decide the data for the next scene
//        val userAccount: UserAccount = null
//        intent.putExtra("flight", userAccount)
//    }
    
}
