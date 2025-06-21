package com.example.exchangelocator.data

import com.example.exchangelocator.models.ExchangePoint

interface ExchangeApi {
    fun getExchangePointsByCurrency(
        deviceLanguage: String,
        currencyCode: String,
    ): List<ExchangePoint>
}