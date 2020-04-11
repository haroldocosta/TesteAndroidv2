package com.haroldocosta.bankapp.homeScreen

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import com.haroldocosta.bankapp.StatementModel
import io.reactivex.annotations.NonNull
import java.lang.ref.WeakReference
import java.util.*


interface HomeRouterInput {
//    fun determineNextScreen(position: Int): Intent?
//    fun passDataToNextScene(position: Int, intent: Intent?)
    fun log()
}

class HomeRouter : HomeRouterInput {
    var activity: WeakReference<HomeActivity>? = null

    var TAG: String = HomeRouter::class.java.simpleName

    override fun log(){
        Log.d(TAG, "LoginRouter")
    }

//    var currentTime: Calendar? = null
//        get() = if (field == null) Calendar.getInstance() else field
//
//    override fun passDataToNextScene(
//        position: Int,
//        intent: Intent
//    ) { //Based on the position or someother data decide the data for the next scene
//        val flight: StatementModel = activity!!.get().listOfVMStatements.get(position)
//        intent.putExtra("flight", flight)
//    }
//
//
//    private fun isFutureStatement(startingTime: Calendar): Boolean {
//        val startTimeInMills = startingTime.timeInMillis
//        val currentTimeInMills = currentTime!!.timeInMillis
//        return startTimeInMills >= currentTimeInMills
//    }

    companion object {
        var TAG = HomeRouter::class.java.simpleName
    }
}
