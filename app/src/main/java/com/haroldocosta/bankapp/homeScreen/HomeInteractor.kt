package com.haroldocosta.bankapp.homeScreen

import io.reactivex.Single
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val homeRepository: HomeRepository) {

    fun getStatements(userId: Int): Single<List<Statement>> {
        return homeRepository.getStatements(userId)
    }

}