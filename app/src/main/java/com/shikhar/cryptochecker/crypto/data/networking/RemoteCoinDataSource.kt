package com.shikhar.cryptochecker.crypto.data.networking

import com.shikhar.cryptochecker.core.data.networking.constructUrl
import com.shikhar.cryptochecker.core.data.networking.safeCall
import com.shikhar.cryptochecker.core.domain.util.NetworkError
import com.shikhar.cryptochecker.core.domain.util.Result
import com.shikhar.cryptochecker.core.domain.util.map
import com.shikhar.cryptochecker.crypto.data.mappers.toCoin
import com.shikhar.cryptochecker.crypto.data.networking.dto.CoinsResponseDto
import com.shikhar.cryptochecker.crypto.domain.Coin
import com.shikhar.cryptochecker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}