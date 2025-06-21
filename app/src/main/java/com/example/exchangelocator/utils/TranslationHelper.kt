package com.example.exchangelocator.utils

import android.content.Context

object TranslationHelper {
    fun translateCountry(context: Context, country: String): String {
        val deviceLanguage = context.resources.configuration.locale.language
        if (deviceLanguage != "he") return country
        return when (country) {
            "USA" -> "ארה״ב"
            "UK" -> "בריטניה"
            "Germany" -> "גרמניה"
            "France" -> "צרפת"
            "Japan" -> "יפן"
            "Israel" -> "ישראל"
            else -> country
        }
    }

    fun translateCity(context: Context, city: String): String {
        val deviceLanguage = context.resources.configuration.locale.language
        if (deviceLanguage != "he") return city
        return when (city) {
            "New York" -> "ניו יורק"
            "London" -> "לונדון"
            "Frankfurt" -> "פרנקפורט"
            "Paris" -> "פריז"
            "Tokyo" -> "טוקיו"
            "Tel Aviv" -> "תל אביב"
            else -> city
        }
    }

    fun translateAddress(context: Context, address: String): String {
        val deviceLanguage = context.resources.configuration.locale.language
        if (deviceLanguage != "he") return address
        return when (address) {
            "JFK International Airport,Terminal 8" -> "JFK שדה תעופה בינלאומי, טרמינל 8"
            "JFK International Airport,Terminal 4" -> "JFK שדה תעופה בינלאומי, טרמינל 4"
            "Heathrow Airport,Terminal 5" -> "שדה תעופה היתרו, טרמינל 5"
            "Heathrow Airport London Underground Station" -> "תחנת רכבת תחתית בשדה תעופה היתרו לונדון"
            "Frankfurt Airport,Terminal 1" -> "שדה תעופה פרנקפורט, טרמינל 1"
            "Charles de Gaulle Airport,Terminal 2E" -> "שדה תעופה שארל דה גול, טרמינל 2E"
            "Narita International Airport,Terminal 1" -> "שדה תעופה בינלאומי נריטה, טרמינל 1"
            "Haneda Airport,Terminal 3" -> "שדה תעופה האנדה, טרמינל 3"
            "Ben Gurion Airport,Terminal 3" -> "שדה תעופה בן גוריון, טרמינל 3"
            else -> address
        }
    }


}