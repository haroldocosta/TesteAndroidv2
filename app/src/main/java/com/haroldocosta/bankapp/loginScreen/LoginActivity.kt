package com.haroldocosta.bankapp.loginScreen

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.haroldocosta.bankapp.AuthPreferences
import com.haroldocosta.bankapp.R
import com.haroldocosta.bankapp.utils.CPFEmailTextWatcher
import com.haroldocosta.bankapp.utils.validations.Validations
import kotlinx.android.synthetic.main.activity_login.*

interface LoginActivityInput {
    fun show(viewModel: LoginViewModel)
}

class LoginActivity  : AppCompatActivity(), LoginActivityInput {

    var TAG: String = LoginActivity::class.java.simpleName
    var output: LoginInteractorInput? = null
    var router: LoginRouter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar!!.hide()
//        val userAccount = AuthPreferences(baseContext).getUserAccount()
//        if(userAccount != null){
//            AuthPreferences(baseContext).navigateToHome(this)
//        }
        user_edit_text.addTextChangedListener(CPFEmailTextWatcher())
        onLoginClick()
        LoginConfigurator.INSTANCE.configure(this)

    }

    override fun show(viewModel: LoginViewModel){
        Log.d(TAG,viewModel.toString())
    }

    private fun onLoginClick() {
        login_button?.setOnClickListener {
            clearErrors()
            val user = user_edit_text?.text?.toString() ?: ""
            val password = password_edit_text?.text?.toString() ?: ""
            val hasError = loginValidates(user, password)
            if(!hasError){
                output!!.login(user, password, baseContext)
            }
        }
    }

    private fun clearErrors() {
        user_edit_text?.error = null
        password_edit_text?.error = null
    }

    private fun loginValidates(user: String, password: String): Boolean{
        if(user.isBlank() || !Validations.isCpfValid(user) && !Validations.isEmailValid(user)){
            Log.d(TAG,user)
            user_edit_text?.error = getString(R.string.login_validation_error_user_invalid)
            return true
        }
        if(password.isBlank()){
            Log.d(TAG,password)
            password_edit_text?.error = getString(R.string.login_validation_error_password_invalid)
            return true
        }
        if(!Validations.hasSpecialCharacter(password)){
            Log.d(TAG,password)
            password_edit_text?.error = getString(R.string.login_validation_error_password_character_special)
            return true
        }
        if(!Validations.hasCapitalizedLetter(password)){
            Log.d(TAG,password)
            password_edit_text?.error = getString(R.string.login_validation_error_password_capitalized_letter)
            return true
        }
        if(!Validations.hasNumber(password)){
            Log.d(TAG,password)
            password_edit_text?.error = getString(R.string.login_validation_error_password_number)
            return true
        }
        return false
    }
}

//interface HomeRouterOutput {
//    ArrayList<FlightViewModel> listOfVMFlights = null;
//     HomeRouter router = null;
//}