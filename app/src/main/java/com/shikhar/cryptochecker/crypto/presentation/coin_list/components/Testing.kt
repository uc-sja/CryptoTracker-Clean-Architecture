package com.shikhar.cryptochecker.crypto.presentation.coin_list.components

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    var count: Int? = null
    var count2: Int = 1

    var count3: Int? = 2
    var count4: Int? = 3


    val myValue: String by lazy {
        println("Computing myValue")
        "Hello, Lazy"
    }

    runBlocking {
      count4 = null
    }

}