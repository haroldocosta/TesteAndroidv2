package com.haroldocosta.bankapp.homeScreen

import androidx.lifecycle.MutableLiveData
import com.haroldocosta.bankapp.loginScreen.UserAccount
import com.haroldocosta.bankapp.managers.SessionManager
import com.haroldocosta.bankapp.utils.ViewState
import com.haroldocosta.bankapp.utils.extensions.addToComposite
import com.haroldocosta.bankapp.utils.extensions.observeOnComputation
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homeInteractor: HomeInteractor
) : BaseViewModel() {

    val userAccountState = MutableLiveData<UserAccount>()
    val statementsResponseState = MutableLiveData<ViewState>()

    fun init() {
        SessionManager.getCurrentUserAccount()?.let { userAccount ->
            userAccountState.postValue(userAccount)
            getStatements(userId = userAccount.userId)
        }
    }

    fun getStatements(userId: Int) {
        compositeDisposable.clear()

        homeInteractor.getStatements(userId)
            .observeOnComputation()
            .doOnSubscribe { statementsResponseState.postValue(ViewState.Loading) }
            .subscribe({ statements ->
                statementsResponseState.postValue(ViewState.Complete(statements))
            }, { error -> handleFailure(error) })
            .addToComposite(compositeDisposable)
    }
}