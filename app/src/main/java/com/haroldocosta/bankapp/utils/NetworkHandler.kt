package com.haroldocosta.bankapp.utils

import android.content.Context
import com.haroldocosta.bankapp.utils.extensions.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Injectable class which handles device network connection.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected ?: false
}