package com.haroldocosta.bankapp.homeScreen

import com.haroldocosta.bankapp.R
import com.haroldocosta.bankapp.loginScreen.UserAccount
import com.haroldocosta.bankapp.managers.NavigationManager
import com.haroldocosta.bankapp.utils.AppException
import com.haroldocosta.bankapp.utils.ViewState
import com.haroldocosta.bankapp.utils.extensions.*
import com.haroldocosta.bankapp.utils.getList
import kotlin.Result.Companion.failure

class HomeActivity : BaseActivity() {

    override val layoutResId = R.layout.home_activity

    private lateinit var viewModel: HomeViewModel

    override fun onInit() {
        viewModel = provideViewModel(viewModelFactory) {
            observe(userAccountState, ::onUserAccountState)
            observe(statementsResponseState, ::onStatementsResponseState)
            failure(appException, ::onAppExceptionError)
            init()
        }
        onLogoutClick()
    }

    private fun onUserAccountState(userAccount: UserAccount?) {
        userAccount?.let {
            homeUserName?.text = userAccount.name
            homeAccount?.text = getString(
                R.string.home_user_account_and_agency,
                userAccount.agency, userAccount.bankAccount
            )
            homeBalance?.text = userAccount.balance.formatCurrency()
        }
    }

    private fun onStatementsResponseState(viewState: ViewState?) {
        when (viewState) {
            ViewState.Loading -> loading(true)
            is ViewState.Complete<*> -> {
                loading(false)
                setupStatements(viewState.getList())
            }
            else -> loading(false)
        }
    }

    private fun onAppExceptionError(appException: AppException?) {
        loading(false)
        checkResponseException(appException) {

        }
    }

    private fun setupStatements(statements: List<Statement>) {
        homeRecyclerView?.apply {
            setLinearLayout(hasFixedSize = true)
            adapter = StatementsAdapter(statements)
        }
    }

    override fun loading(isLoading: Boolean) {
        super.loading(isLoading)
        if (isLoading) homeStatementsProgress.visible() else homeStatementsProgress.gone()
    }

    private fun onLogoutClick() {
        homeLogout?.setOnClickListener {
            NavigationManager.logout(this)
        }
    }
}