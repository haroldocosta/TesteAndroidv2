package com.haroldocosta.bankapp

import android.content.Context
import com.haroldocosta.bankapp.homeScreen.HomeResponse
import com.haroldocosta.bankapp.loginScreen.LoginResponse
import com.haroldocosta.bankapp.loginScreen.UserAccount
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("user") user: String,
        @Field("password") password: String
    ): Call<LoginResponse>


    @GET("statements/{userId}")
    fun getStatements(@Path("userId") userId: Int): Call<HomeResponse>

}
