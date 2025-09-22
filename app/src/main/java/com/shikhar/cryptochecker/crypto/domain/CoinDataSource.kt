package com.shikhar.cryptochecker.crypto.domain


import com.shikhar.cryptochecker.core.domain.util.NetworkError
import com.shikhar.cryptochecker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}