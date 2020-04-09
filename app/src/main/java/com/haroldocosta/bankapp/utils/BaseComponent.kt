package com.haroldocosta.bankapp.utils

import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi

/**
 * Function to get the current [BaseComponent] instance
 */
fun base() = BaseComponent.INSTANCE

/**
 * Base component to all Dagger components
 */
interface BaseComponent {
    /**
     * The [PreferencesHelper] injected by Dagger
     */
    val preferencesHelper: PreferencesHelper

    /**
     * The [ViewModelProvider.Factory] injected by Dagger
     */
    val viewModelFactory: ViewModelProvider.Factory

    val moshi: Moshi

    companion object {
        lateinit var INSTANCE: BaseComponent
    }
}