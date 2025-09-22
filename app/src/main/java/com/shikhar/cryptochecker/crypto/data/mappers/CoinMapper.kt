package com.shikhar.cryptochecker.crypto.data.mappers


import com.shikhar.cryptochecker.crypto.data.networking.dto.CoinDto
import com.shikhar.cryptochecker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}