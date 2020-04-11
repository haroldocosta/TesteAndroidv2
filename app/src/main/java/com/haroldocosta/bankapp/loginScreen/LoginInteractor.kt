package com.haroldocosta.bankapp.loginScreen

import android.content.Context
import android.util.Log
import com.haroldocosta.bankapp.AuthPreferences
import com.haroldocosta.bankapp.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface LoginInteractorInput {
    fun fetchLoginMetaData(request: LoginRequest?)
    fun login(user: String, password: String, context: Context)
}

class LoginInteractor : LoginInteractorInput {
    var output: LoginPresenterInput? = null

    override fun fetchLoginMetaData(request: LoginRequest?) {
        Log.e(TAG, "In method fetchLoginMetaData")
        Log.d(TAG,request.toString())
        var loginResponse = null
        output!!.presentLoginMetaData(loginResponse)
    }

    override fun login(user: String,password: String, context: Context){
        RetrofitClient.instance.login(user, password)
            .enqueue(object: Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(TAG, "falha"+t.message)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d(TAG, "ok"+response.body()?.userAccount.toString())
                    Log.d(TAG, response.body()?.error.toString())
                    if(response.body()?.error?.code == null){
                        Log.d(TAG, "autenticado")
                        AuthPreferences(context).setUserAccount(response.body()?.userAccount!!)
                        val savedUser = AuthPreferences(context).getUserAccount()
                        Log.d(TAG, savedUser.toString())
                        if(savedUser != null){
                            AuthPreferences(context).navigateToHome(context)
                        }
                    }
                }
            })
    }

    companion object {
        var TAG = LoginInteractor::class.java.simpleName
    }
}
