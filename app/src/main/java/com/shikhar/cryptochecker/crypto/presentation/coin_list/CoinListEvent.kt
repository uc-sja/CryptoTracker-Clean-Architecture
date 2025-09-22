package com.shikhar.cryptochecker.crypto.presentation.coin_list

import com.shikhar.cryptochecker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}