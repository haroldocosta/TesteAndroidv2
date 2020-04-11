package com.haroldocosta.bankapp.homeScreen

import android.util.Log
import com.haroldocosta.bankapp.StatementViewModel
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.ArrayList

interface HomePresenterInput {
    fun presentHomeMetaData(response: HomeResponse?)
}

class HomePresenter : HomePresenterInput {
    var output: WeakReference<HomeActivityInput>? = null

    override fun presentHomeMetaData(response: HomeResponse?) { // Log.e(TAG, "presentHomeMetaData() called with: response = [" + response + "]");
//Do your decoration or filtering here
        val homeViewModel = HomeViewModel()
        homeViewModel.statementList = response?.statementList
        if (homeViewModel?.statementList != null) {
            Log.d(TAG,homeViewModel.toString())
            output!!.get()?.displayHomeMetaData(homeViewModel)
        }
    }

    companion object {
        var TAG = HomePresenter::class.java.simpleName
    }
}
