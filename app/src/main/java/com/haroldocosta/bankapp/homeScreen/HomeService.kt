package com.haroldocosta.bankapp.homeScreen

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @GET("statements/{userId}")
    fun getStatements(@Path("userId") userId: Int): Single<StatementsResponse>
}