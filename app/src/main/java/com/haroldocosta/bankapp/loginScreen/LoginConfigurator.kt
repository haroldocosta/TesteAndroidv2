package com.haroldocosta.bankapp.loginScreen

import java.lang.ref.WeakReference

/**
 * Created by mkaratadipalayam on 10/10/16.
 */
enum class HomeConfigurator {
    INSTANCE;

    fun configure(activity: LoginActivity) {
        val router = LoginRouter()
        router.activity = WeakReference<LoginActivity>(activity)
        val presenter = LoginPresenter()
        presenter.output = WeakReference<LoginActivityInput>(activity)
        val interactor = LoginInteractor()
        interactor.output = presenter
        if (activity.output == null) {
            activity.output = interactor
        }
        if (activity.router == null) {
            activity.router = router
        }
    }
}