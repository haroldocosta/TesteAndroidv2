package com.haroldocosta.bankapp.homeScreen

import com.haroldocosta.bankapp.StatementModel
import com.haroldocosta.bankapp.StatementViewModel
import com.haroldocosta.bankapp.loginScreen.ErrorResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*


class HomeModel
data class HomeViewModel (
    var statementList: List<StatementRaw>? = null
)

data class HomeRequest (
    var userId: Int? = null
)

//data class HomeResponse (
//    var statementList: ArrayList<StatementModel>?,
//    var error: ErrorResponse
//)

@JsonClass(generateAdapter = true)
data class HomeResponse(
    @property:Json(name = "statementList") val statementList: List<StatementRaw>?,
    val error: ErrorResponse
)

@JsonClass(generateAdapter = true)
data class StatementRaw(
    @Json(name = "title") var title: String?,
    @Json(name = "desc") var desc: String?,
    @Json(name = "date") var date: String?,
    @Json(name = "value") var value: Double?
)