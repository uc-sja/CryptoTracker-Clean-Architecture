package com.shikhar.cryptochecker.crypto.domain

import com.shikhar.cryptochecker.crypto.domain.Coin
import com.shikhar.cryptochecker.core.domain.util.NetworkError
import com.shikhar.cryptochecker.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>

}