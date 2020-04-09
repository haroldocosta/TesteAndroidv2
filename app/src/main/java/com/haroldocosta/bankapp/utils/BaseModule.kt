package com.haroldocosta.bankapp.utils

import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class BaseModule {

    @Binds
    internal abstract fun provideBaseViewModelFactory(baseViewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Singleton
        fun provideMoshi(): Moshi = Moshi.Builder().build()

        @Provides
        @JvmStatic
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences =
            context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)

    }

}