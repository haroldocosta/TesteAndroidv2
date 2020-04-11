package com.haroldocosta.bankapp.homeScreen

import android.content.Context
import android.util.Log
import com.haroldocosta.bankapp.ArrayEmptyException
import com.haroldocosta.bankapp.AuthPreferences
import com.haroldocosta.bankapp.RetrofitClient
import com.haroldocosta.bankapp.StatementModel
import com.haroldocosta.bankapp.loginScreen.LoginInteractor
import com.haroldocosta.bankapp.loginScreen.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface HomeInteractorInput {
    fun fetchHomeMetaData(request: HomeRequest?)
    fun logout(context: Context)
}

class HomeInteractor : HomeInteractorInput {
    var output: HomePresenterInput? = null

    override fun logout(context: Context) {
        AuthPreferences(context).logout(context)
    }

    override fun fetchHomeMetaData(request: HomeRequest?) {
        Log.e(TAG, "In method fetchHomeMetaData")
        Log.d(TAG, request?.userId.toString())
        RetrofitClient.instance.getStatements(request?.userId!!)
            .enqueue(object: Callback<HomeResponse> {
                override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                    Log.d(TAG, t.message!!)
                }

                override fun onResponse(
                    call: Call<HomeResponse>,
                    response: Response<HomeResponse>
                ) {
                    Log.d(TAG, response.toString())
                    Log.d(TAG, response.body()!!.toString())
                    Log.d(TAG, response.body()?.error?.code.toString())

                    if(response.body()?.error?.code == null){
                        val homeResponse: HomeResponse? = response.body()
                        Log.d(TAG, "response.body()?.error?.code é null")

                        val list = homeResponse?.statementList
                        if (null == list || list.isEmpty()) {
                            throw ArrayEmptyException("Empty Statement List")
                        }
                        Log.d(TAG, "@@@@ ${homeResponse}")
                        output?.presentHomeMetaData(homeResponse)
                    }
                }
            })
    }

    companion object {
        var TAG = HomeInteractor::class.java.simpleName
    }
}
