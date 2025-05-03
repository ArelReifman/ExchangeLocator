package com.example.exchangelocator.Data.models.repository

import android.app.Application
import com.example.exchangelocator.Data.models.CoinDetail
import com.example.exchangelocator.Data.models.Local_DB.CoinDB
import com.example.exchangelocator.Data.models.Local_DB.CoinDao

class CoinRepository(application: Application) {
    private var coinDao: CoinDao?

    init {
        val db = CoinDB.getDatabase(application.applicationContext)
        coinDao = db?.getCoinDao()
    }
    fun addCoin(coin: CoinDetail) = coinDao?.addCoin(coin)

    fun getAllCoins() = coinDao?.getAllCoins()

    fun deleteCoin(coin: CoinDetail) = coinDao?.deleteCoin(coin)

    fun updateCoin(coin: CoinDetail) = coinDao?.updateCoin(coin)

}