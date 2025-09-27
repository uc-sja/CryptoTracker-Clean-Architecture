package com.shikhar.cryptochecker.crypto.data.networking

import android.net.Network
import com.shikhar.cryptochecker.core.data.networking.constructUrl
import com.shikhar.cryptochecker.core.data.networking.safeCall
import com.shikhar.cryptochecker.core.domain.util.NetworkError
import com.shikhar.cryptochecker.core.domain.util.Result
import com.shikhar.cryptochecker.core.domain.util.map
import com.shikhar.cryptochecker.crypto.data.mappers.toCoin
import com.shikhar.cryptochecker.crypto.data.mappers.toCoinPrice
import com.shikhar.cryptochecker.crypto.data.networking.dto.CoinHistoryDto
import com.shikhar.cryptochecker.crypto.data.networking.dto.CoinsResponseDto
import com.shikhar.cryptochecker.crypto.domain.CoinDataSource
import com.shikhar.cryptochecker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime
import com.shikhar.cryptochecker.crypto.domain.Coin

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        //breaking code chains for better readability
        val safeCall: Result<CoinsResponseDto, NetworkError> = safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }

        val coinListResult: Result<List<Coin>, NetworkError> =
            safeCall.map { response: CoinsResponseDto ->
                val coinList: List<Coin> = response.data.map { it.toCoin() }
                // The last expression in this lambda is its return value
                coinList
            }

        return coinListResult
    }


    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }


}