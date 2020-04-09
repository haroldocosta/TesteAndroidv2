package com.haroldocosta.bankapp.loginScreen

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserAccount(
    var userId: Int,
    var name: String,
    var bankAccount: String,
    var agency: String,
    var balance: Double
)


@JsonClass(generateAdapter = true)
data class UserAccountResponse(
    @property:Json(name = "userAccount")
    val data: UserAccountRaw?,
    val error: Throwable
)


@JsonClass(generateAdapter = true)
data class UserAccountRaw(
    @Json(name = "userId") var userId: Int?,
    @Json(name = "name") var name: String?,
    @Json(name = "bankAccount") var bankAccount: String?,
    @Json(name = "agency") var agency: String?,
    @Json(name = "balance") var balance: Double?
)

