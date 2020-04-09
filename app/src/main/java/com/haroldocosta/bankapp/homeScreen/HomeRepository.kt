package com.haroldocosta.bankapp.homeScreen

import com.haroldocosta.bankapp.utils.NetworkHandler

import io.reactivex.Single
import javax.inject.Inject

interface HomeRepository {

    fun getStatements(userId: Int): Single<List<Statement>>

    class Remote
    @Inject constructor(
        private val statementMapper: StatementMapper,
        private val homeService: HomeService,
        networkHandler: NetworkHandler
    ) : BaseRemoteRepository(networkHandler), HomeRepository {

        override fun getStatements(userId: Int): Single<List<Statement>> =
            request(statementMapper) {
                homeService.getStatements(userId)
            }
    }
}