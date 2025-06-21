package com.example.exchangelocator.data.local

import com.example.exchangelocator.data.ExchangeApi
import com.example.exchangelocator.data.local.ExchangePointsConst.exchangePoints
import com.example.exchangelocator.models.ExchangePoint

class ExchangePointLocalProvider : ExchangeApi {
    override fun getExchangePointsByCurrency(
        deviceLanguage: String,
        currencyCode: String,
    ): List<ExchangePoint> {

        return when (currencyCode) {
            "USD" -> exchangePoints.filter { it.id in 1..2 }
            "GBP" -> exchangePoints.filter { it.id in 3..4 }
            "EUR" -> exchangePoints.filter { it.id in 5..6 }
            "JPY" -> exchangePoints.filter { it.id in 7..8 }
            "ILS" -> exchangePoints.filter { it.id in 9..10 }
            else -> emptyList()
        }
    }
}