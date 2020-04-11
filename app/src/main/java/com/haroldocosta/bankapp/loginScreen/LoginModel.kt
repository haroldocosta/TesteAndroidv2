package com.haroldocosta.bankapp.loginScreen

import java.util.*


class LoginModel
data class LoginViewModel (
    //TODO - filter to have only the needed data
    var user: String?,
    var password: String?
)

data class LoginRequest (
    var loginViewModel: LoginViewModel
)

data class LoginResponse (
    var userAccount : UserAccount,
    var error : ErrorResponse
)

data class UserAccount (
    var userId : Int?,
    var name : String?,
    var bankAccount : String?,
    var agency : String?,
    var balance : Double?
)

data class ErrorResponse (
    var code : Int?,
    var message : String?
)