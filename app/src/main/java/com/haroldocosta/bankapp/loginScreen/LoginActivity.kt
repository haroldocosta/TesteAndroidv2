package com.haroldocosta.bankapp.loginScreen

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.haroldocosta.bankapp.R
import com.haroldocosta.bankapp.managers.NavigationManager
import com.haroldocosta.bankapp.utils.*
import com.haroldocosta.bankapp.utils.extensions.*
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

// ENTRADA E SAIDA


class LoginActivity: AppCompatActivity() {


    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)


//        viewModel = provideViewModel() {
//            observe(loginResponseState, ::onLoginResponseState)
//            failure(appException, ::onAppExceptionError)
//        }

        if (isAuthenticated()) NavigationManager.navigateToHome(this)
        user_edit_text.addTextChangedListener(CPFEmailTextWatcher())
        onLoginClick()
    }

    private fun onAppExceptionError(appException: AppException?) {
        loading(false)
        checkResponseException(appException) { exception ->
            when (exception) {
                LoginBusiness.UserInvalid -> {
                    user_edit_text.error =
                        getString(R.string.login_validation_error_user_invalid)
                }
                LoginBusiness.PasswordInvalid -> {
                    password_edit_text.error =
                        getString(R.string.login_validation_error_password_invalid)
                }
                LoginBusiness.PasswordNeedSpecialCharacter -> {
                    password_edit_text.error =
                        getString(R.string.login_validation_error_password_character_special)
                }
                LoginBusiness.PasswordNeedCapitalizedLetter -> {
                    password_edit_text.error =
                        getString(R.string.login_validation_error_password_capitalized_letter)
                }
                LoginBusiness.PasswordNeedNumber -> {
                    password_edit_text.error =
                        getString(R.string.login_validation_error_password_number)
                }
            }
        }
    }

    private fun onLoginClick() {
        login_button?.setOnClickListener {
            clearErrors()
            val user = user_edit_text?.text?.toString() ?: ""
            val password = password_edit_text?.text?.toString() ?: ""

            viewModel.login(user, password)
        }
    }

    private fun clearErrors() {
        user_edit_text?.error = null
        password_edit_text?.error = null
    }

    fun isAuthenticated(): Boolean{
        return false
    }
    fun loading(isLoading: Boolean) {
        if (isLoading) loginProgress.visible() else loginProgress.gone()
        val enabledComponents = !isLoading
        user_edit_text?.isEnabled = enabledComponents
        password_edit_text?.isEnabled = enabledComponents
        login_button?.isEnabled = enabledComponents
    }


    fun checkResponseException(
        appException: AppException?,
        body: (AppException?) -> Unit
    ) {
        Timber.e(appException)
        when (appException) {
            NetworkError -> {
                onNetworkWithoutConnection()
            }
            UnauthorizedError -> {
                onUnAuthorized()
            }
            HttpError -> {
                onHttpError()
            }
            UnknownException -> {
                onUnknownError()
            }
            InvalidCredentials -> onInvalidCredentials()
            is ServerError -> {
                onResponseError(appException.error)
            }
            else -> {
                body(appException)
            }
        }
    }

    /**
     * Function called when handled a Http generic exception
     */
    open fun onHttpError() {}

    /**
     * Function called when there is no internet connection
     */
    open fun onNetworkWithoutConnection() {

    }

    open fun showServerError(error: ResponseError, duration: Int = 3000) {
        error.message?.let { toast(it) }
    }

    /**
     * Function called when user session expired or user is invalid
     */
    open fun onUnAuthorized() {}

    open fun onInvalidCredentials() {

    }

    open fun onResponseError(error: ResponseError) {
        Timber.e(error.toString())
        showServerError(error)
    }

    open fun onUnknownError() {

    }

}
