package com.shikhar.cryptochecker.crypto.data.networking.dto

import kotlinx.serialization.Serializable


@Serializable
data class CoinHistoryDto(
    val data: List<CoinPriceDto>
)
