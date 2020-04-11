package com.haroldocosta.bankapp.loginScreen

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.haroldocosta.bankapp.AuthPreferences
import com.haroldocosta.bankapp.R
import com.haroldocosta.bankapp.utils.CPFEmailTextWatcher
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
            val user = user_edit_text?.text?.toString() ?: ""
            val password = password_edit_text?.text?.toString() ?: ""
            output!!.login(user, password, baseContext)
        }
    }
}

//interface HomeRouterOutput {
//    ArrayList<FlightViewModel> listOfVMFlights = null;
//     HomeRouter router = null;
//}