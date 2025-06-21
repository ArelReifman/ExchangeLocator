package com.example.exchangelocator.repository

import android.app.Application
import com.example.exchangelocator.data.ExchangeApi
import com.example.exchangelocator.data.local.CoinDB
import com.example.exchangelocator.data.local.CoinDao
import com.example.exchangelocator.data.local.ExchangePointLocalProvider
import com.example.exchangelocator.getDeviceLanguage
import com.example.exchangelocator.models.CoinDetail
import com.example.exchangelocator.models.ExchangePoint

class CoinRepository(
    private val application: Application,
) {
    private var coinDao: CoinDao = CoinDB.getDatabase(application.applicationContext).getCoinDao()
    private val exchangeApi: ExchangeApi = ExchangePointLocalProvider()

    suspend fun addCoin(coin: CoinDetail) = coinDao.addCoin(coin)

    fun getAllCoinsLiveData() = coinDao.getAllCoins()

    suspend fun deleteCoin(coin: CoinDetail) = coinDao.deleteCoin(coin)

    fun getExchangePointsByCurrency(
        currencyCode: String,
    ): List<ExchangePoint> {
        return exchangeApi.getExchangePointsByCurrency(
            deviceLanguage = application.baseContext.getDeviceLanguage(),
            currencyCode = currencyCode,
        )
    }
}