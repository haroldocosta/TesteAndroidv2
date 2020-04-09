package com.haroldocosta.bankapp.managers

import com.haroldocosta.bankapp.app
import com.haroldocosta.bankapp.utils.extensions.fromJson
import com.haroldocosta.bankapp.utils.extensions.toJson
import com.haroldocosta.bankapp.loginScreen.UserAccount
import javax.inject.Inject

object SessionManager {

    private var userAccount: UserAccount? = null

    fun setCurrentUserAccount(userAccount: UserAccount) {
        this.userAccount = userAccount
        val userAccountJson = app().moshi.toJson(userAccount)
        app().preferencesHelper.user = userAccountJson
    }

    fun getCurrentUserAccount(): UserAccount? {
        return if (this.userAccount != null)
            this.userAccount!!
        else {
            this.userAccount = app().moshi.fromJson(app().preferencesHelper.user)
            userAccount
        }
    }

    fun logout() {
        app().preferencesHelper.logout()
    }

    class SessionManagerInject @Inject constructor() {

        fun setCurrentUserAccount(userAccount: UserAccount) {
            SessionManager.setCurrentUserAccount(userAccount)
        }
    }
}