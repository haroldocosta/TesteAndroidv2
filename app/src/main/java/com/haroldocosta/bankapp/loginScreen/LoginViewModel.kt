package com.haroldocosta.bankapp.loginScreen

import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.haroldocosta.bankapp.managers.SessionManager
import com.haroldocosta.bankapp.utils.ViewState
import com.haroldocosta.bankapp.utils.extensions.addToComposite
import com.haroldocosta.bankapp.utils.extensions.observeOnComputation
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginInteractor: LoginInteractor,
    private val sessionManagerInject: SessionManager.SessionManagerInject
){

    val loginResponseState = MutableLiveData<ViewState>()

    fun login(user: String, password: String) {
        CompositeDisposable().clear()
        loginInteractor.login(user, password)
            .observeOnComputation()
            .doOnSubscribe { loginResponseState.postValue(ViewState.Loading) }
            .subscribe({ userAccount ->
                sessionManagerInject.setCurrentUserAccount(userAccount)
                loginResponseState.postValue(ViewState.Success)
            }, { error -> println(error) })
            .addToComposite(CompositeDisposable())
    }
}

class LoginViewModelFactory constructor(private val repository: LoginInteractor, private val application: SessionManager.SessionManagerInject) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(repository, application)
                else ->
                    throw IllegalArgumentException("Classe desconhecida.")
            }
        } as T
}

inline fun <reified T : ViewModel> FragmentActivity.provideViewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

