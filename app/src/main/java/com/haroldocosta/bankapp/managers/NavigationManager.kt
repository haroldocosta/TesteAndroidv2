package com.haroldocosta.bankapp.managers

import android.content.Context
import com.haroldocosta.bankapp.homeScreen.HomeActivityArgs

object NavigationManager {

    fun navigateToLogin(context: Context?) {
        context?.let {
            LoginActivityArgs().launch(
                context = context,
                finishCurrent = true
            )
        }
    }

    fun logout(context: Context?) {
        SessionManager.logout()
        navigateToLogin(context)
    }

    fun navigateToHome(context: Context?) {
        context?.let {
            HomeActivityArgs().launch(
                context = context,
                finishCurrent = true
            )
        }
    }
}