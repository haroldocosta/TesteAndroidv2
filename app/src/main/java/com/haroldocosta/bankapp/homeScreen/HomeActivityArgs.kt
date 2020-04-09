package com.haroldocosta.bankapp.homeScreen

import android.content.Context
import android.content.Intent
import com.haroldocosta.bankapp.utils.ActivityArgs

class HomeActivityArgs : ActivityArgs {

    override fun intent(context: Context) = Intent(context, HomeActivity::class.java)

}