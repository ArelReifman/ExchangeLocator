package com.example.exchangelocator.data.local

import com.example.exchangelocator.data.ExchangeApi
import com.example.exchangelocator.data.local.ExchangePointsConst.addressTranslations
import com.example.exchangelocator.data.local.ExchangePointsConst.countryAndCityTranslations
import com.example.exchangelocator.data.local.ExchangePointsConst.exchangePoints
import com.example.exchangelocator.models.ExchangePoint

class ExchangePointLocalProvider : ExchangeApi {
    override fun getExchangePointsByCurrency(
        deviceLanguage: String,
        currencyCode: String,
    ): List<ExchangePoint> {
        val exchangePointsForCurrency = when (currencyCode) {
            "USD" -> exchangePoints.filter { it.id in 1..2 }
            "GBP" -> exchangePoints.filter { it.id in 3..4 }
            "EUR" -> exchangePoints.filter { it.id in 5..6 }
            "JPY" -> exchangePoints.filter { it.id in 7..8 }
            "ILS" -> exchangePoints.filter { it.id in 9..10 }
            else -> emptyList()
        }


        val languageTranslations =
            countryAndCityTranslations[deviceLanguage] ?: return exchangePointsForCurrency
        val streetTranslations = addressTranslations[deviceLanguage]
        return exchangePointsForCurrency.map { originalPoint ->
            val translatedStreet =
                if (streetTranslations != null && streetTranslations.containsKey(originalPoint.street)) {
                    streetTranslations[originalPoint.street] ?: originalPoint.street
                } else {

                    var tempStreet = originalPoint.street
                    languageTranslations.forEach { (key, value) ->
                        if (tempStreet.contains(key)) {
                            tempStreet = tempStreet.replace(key, value)
                        }
                    }
                    tempStreet
                }

            ExchangePoint(
                id = originalPoint.id,
                name = originalPoint.name,
                country = languageTranslations[originalPoint.country] ?: originalPoint.country,
                city = languageTranslations[originalPoint.city] ?: originalPoint.city,
                street = translatedStreet,
                openingHours = originalPoint.openingHours,
                imageResId = originalPoint.imageResId
            )
        }
    }
}