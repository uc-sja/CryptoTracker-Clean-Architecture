package com.shikhar.cryptochecker.crypto.presentation.coin_list

import com.shikhar.cryptochecker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}