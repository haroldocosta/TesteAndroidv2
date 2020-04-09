package com.haroldocosta.bankapp.homeScreen

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.math.BigDecimal

@JsonClass(generateAdapter = true)
data class StatementsResponse(
    @property:Json(name = "statementList") override val data: List<StatementRaw>?,
    override val error: ResponseError
) : BaseResponse(data, error)


@JsonClass(generateAdapter = true)
data class StatementRaw(
    @Json(name = "title") var title: String?,
    @Json(name = "desc") var desc: String?,
    @Json(name = "date") var date: String?,
    @Json(name = "value") var value: Double?
)

data class Statement(
    var title: String,
    var desc: String,
    var date: String,
    var value: BigDecimal
)