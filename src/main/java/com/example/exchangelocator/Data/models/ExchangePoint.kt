package com.example.exchangelocator.Data.models

import android.content.Context
import com.example.exchangelocator.R

data class ExchangePoint(
    val id: Int,
    val name: String,
    val country: String,
    val city: String,
    val street: String,
    val openingHours: String,
    val imageResId: Int
)

object ExchangePointProvider {

    private val exchangePoints = listOf(
        ExchangePoint(1, "CXI Currency Exchange", "USA", "New York", "JFK International Airport,Terminal 8", "06:00-23:00", R.drawable.cxi_us),
        ExchangePoint(2, "Travelex", "USA", "New York", "JFK International Airport,Terminal 4", "06:00-22:00", R.drawable.travelex_us),
        ExchangePoint(3, "Travelex", "UK", "London", "Heathrow Airport,Terminal 5", "05:00-23:00", R.drawable.travelex_uk),
        ExchangePoint(4, "ICE Currency Exchange", "UK", "London", "Heathrow Airport London Underground Station", "07:30-20:00", R.drawable.ice_uk),
        ExchangePoint(5, "Travelex Germany", "Germany", "Frankfurt", "Frankfurt Airport,Terminal 1", "06:00-22:00", R.drawable.travelex_gr),
        ExchangePoint(6, "Travelex France", "France", "Paris", "Charles de Gaulle Airport,Terminal 2E", "05:30-22:30", R.drawable.travelex_fr),
        ExchangePoint(7, "GPA Currency Exchange", "Japan", "Tokyo", "Narita International Airport,Terminal 1", "06:30-23:00", R.drawable.gpa_tokyo),
        ExchangePoint(8, "Mizuho Bank", "Japan", "Tokyo", "Haneda Airport,Terminal 3", "24/7", R.drawable.mizuho_bank_tokyo),
        ExchangePoint(9, "Change Place", "Israel", "Tel Aviv", "Ben Gurion Airport,Terminal 3", "24/7", R.drawable.bank_hapoalim_tlv),
        ExchangePoint(10, "Global Exchange", "Israel", "Tel Aviv", "Ben Gurion Airport,Terminal 3", "24/7", R.drawable.global_exchange_tlv)
    )

    private val countryAndCityTranslations = mapOf(
        "he" to mapOf(
            "USA" to "ארה״ב",
            "UK" to "בריטניה",
            "Germany" to "גרמניה",
            "France" to "צרפת",
            "Japan" to "יפן",
            "Israel" to "ישראל",
            "New York" to "ניו יורק",
            "London" to "לונדון",
            "Frankfurt" to "פרנקפורט",
            "Paris" to "פריז",
            "Tokyo" to "טוקיו",
            "Tel Aviv" to "תל אביב",
            "Airport" to "שדה תעופה",
            "Terminal" to "טרמינל",
            "International Airport" to "שדה תעופה בינלאומי",
            "Underground Station" to "תחנת רכבת תחתית",
            "JFK" to "JFK",
            "Heathrow" to "היתרו",
            "Frankfurt Airport" to "שדה תעופה פרנקפורט",
            "Charles de Gaulle" to "שארל דה גול",
            "Narita" to "נריטה",
            "Haneda" to "האנדה",
            "Ben Gurion" to "בן גוריון"
        )
    )


    private val addressTranslations = mapOf(
        "he" to mapOf(
            "JFK International Airport,Terminal 8" to "JFK שדה תעופה בינלאומי, טרמינל 8",
            "JFK International Airport,Terminal 4" to "JFK שדה תעופה בינלאומי, טרמינל 4",
            "Heathrow Airport,Terminal 5" to "שדה תעופה היתרו, טרמינל 5",
            "Heathrow Airport London Underground Station" to "תחנת רכבת תחתית בשדה תעופה היתרו לונדון",
            "Frankfurt Airport,Terminal 1" to "שדה תעופה פרנקפורט, טרמינל 1",
            "Charles de Gaulle Airport,Terminal 2E" to "שדה תעופה שארל דה גול, טרמינל 2E",
            "Narita International Airport,Terminal 1" to "שדה תעופה בינלאומי נריטה, טרמינל 1",
            "Haneda Airport,Terminal 3" to "שדה תעופה האנדה, טרמינל 3",
            "Ben Gurion Airport,Terminal 3" to "שדה תעופה בן גוריון, טרמינל 3"
        )
    )

    fun getExchangePointsByCurrency(currencyCode: String): List<ExchangePoint> {
        return getExchangePointsByCurrency(null, currencyCode)
    }

    fun getExchangePointsByCurrency(appContext: Context?, currencyCode: String): List<ExchangePoint> {
        val exchangePointsForCurrency = when (currencyCode) {
            "USD" -> exchangePoints.filter { it.id in 1..2 }
            "GBP" -> exchangePoints.filter { it.id in 3..4 }
            "EUR" -> exchangePoints.filter { it.id in 5..6 }
            "JPY" -> exchangePoints.filter { it.id in 7..8 }
            "ILS" -> exchangePoints.filter { it.id in 9..10 }
            else -> emptyList()
        }

        if (appContext == null) {
            return exchangePointsForCurrency
        }

        val deviceLanguage = appContext.resources.configuration.locale.language

        val languageTranslations = countryAndCityTranslations[deviceLanguage] ?: return exchangePointsForCurrency
        val streetTranslations = addressTranslations[deviceLanguage]

        return exchangePointsForCurrency.map { originalPoint ->


            val translatedStreet = if (streetTranslations != null && streetTranslations.containsKey(originalPoint.street)) {
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