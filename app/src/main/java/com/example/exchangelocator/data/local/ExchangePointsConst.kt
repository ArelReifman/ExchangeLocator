package com.example.exchangelocator.data.local

import com.example.exchangelocator.R
import com.example.exchangelocator.models.ExchangePoint

object ExchangePointsConst {
    val exchangePoints = listOf(
        ExchangePoint(
            1,
            "CXI Currency Exchange",
            "USA",
            "New York",
            "JFK International Airport,Terminal 8",
            "06:00-23:00",
            R.drawable.cxi_us
        ),
        ExchangePoint(
            2,
            "Travelex",
            "USA",
            "New York",
            "JFK International Airport,Terminal 4",
            "06:00-22:00",
            R.drawable.travelex_us
        ),
        ExchangePoint(
            3,
            "Travelex",
            "UK",
            "London",
            "Heathrow Airport,Terminal 5",
            "05:00-23:00",
            R.drawable.travelex_uk
        ),
        ExchangePoint(
            4,
            "ICE Currency Exchange",
            "UK",
            "London",
            "Heathrow Airport London Underground Station",
            "07:30-20:00",
            R.drawable.ice_uk
        ),
        ExchangePoint(
            5,
            "Travelex Germany",
            "Germany",
            "Frankfurt",
            "Frankfurt Airport,Terminal 1",
            "06:00-22:00",
            R.drawable.travelex_gr
        ),
        ExchangePoint(
            6,
            "Travelex France",
            "France",
            "Paris",
            "Charles de Gaulle Airport,Terminal 2E",
            "05:30-22:30",
            R.drawable.travelex_fr
        ),
        ExchangePoint(
            7,
            "GPA Currency Exchange",
            "Japan",
            "Tokyo",
            "Narita International Airport,Terminal 1",
            "06:30-23:00",
            R.drawable.gpa_tokyo
        ),
        ExchangePoint(
            8,
            "Mizuho Bank",
            "Japan",
            "Tokyo",
            "Haneda Airport,Terminal 3",
            "24/7",
            R.drawable.mizuho_bank_tokyo
        ),
        ExchangePoint(
            9,
            "Change Place",
            "Israel",
            "Tel Aviv",
            "Ben Gurion Airport,Terminal 3",
            "24/7",
            R.drawable.bank_hapoalim_tlv
        ),
        ExchangePoint(
            10,
            "Global Exchange",
            "Israel",
            "Tel Aviv",
            "Ben Gurion Airport,Terminal 3",
            "24/7",
            R.drawable.global_exchange_tlv
        )
    )

    val countryAndCityTranslations = mapOf(
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


    val addressTranslations = mapOf(
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
}