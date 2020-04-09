package com.haroldocosta.bankapp.loginScreen

import timber.log.Timber
import javax.inject.Inject

class UserAccountMapper @Inject constructor(){
    val trackException: (Throwable) -> Unit = { exception ->
        Timber.i("Send error to Crashlytics $exception")
    }

    fun copyValues(raw: UserAccountResponse): UserAccount {
        val userAccountRaw = raw.data!!
        return UserAccount(
            userId = userAccountRaw.userId!!,
            name = userAccountRaw.name!!,
            bankAccount = userAccountRaw.bankAccount!!,
            agency = userAccountRaw.agency!!,
            balance = userAccountRaw.balance!!
        )
    }
}