package com.example.exchangelocator.Data.models

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

    val exchangePoints = listOf(

        ExchangePoint(1, "CXI Currency Exchange", "USA", "New York", "JFK International Airport, Terminal 8, Arrivals", "06:00-23:00", R.drawable.cxi_us),
        ExchangePoint(2, "Travelex", "USA", "New York", "JFK International Airport, Terminal 4, Departures, Concourse B Gate 31", "06:00-22:00", R.drawable.travelex_us),


        ExchangePoint(3, "Travelex", "UK", "London", "Heathrow Airport, Terminal 5, After Security", "05:00-23:00", R.drawable.travelex_uk),
        ExchangePoint(4, "ICE Currency Exchange", "UK", "London", "Heathrow Airport London Underground Station", "07:30-20:00", R.drawable.ice_uk),


        ExchangePoint(5, "Travelex", "Germany", "Frankfurt", "Frankfurt Airport, Terminal 1, Arrivals Hall", "06:00-22:00", R.drawable.travelex_gr),
        ExchangePoint(6, "Travelex", "France", "Paris", "Charles de Gaulle Airport, Terminal 2E", "05:30-22:30", R.drawable.travelex_fr),


        ExchangePoint(7, "GPA Currency Exchange", "Japan", "Tokyo", "Narita International Airport, Terminal 1, Arrivals South Wing", "06:30-23:00", R.drawable.gpa_tokyo),
        ExchangePoint(8, "Mizuho Bank", "Japan", "Tokyo", "Haneda Airport, Terminal 3, 2nd Floor (International Arrivals)", "24/7", R.drawable.mizuho_bank_tokyo),


        ExchangePoint(9, "Change Place", "Israel", "Tel Aviv", "Ben Gurion Airport, Terminal 3, Arrivals Hall", "24/7", R.drawable.bank_hapoalim_tlv),
        ExchangePoint(10, "Global Exchange", "Israel", "Tel Aviv", "Ben Gurion Airport, Terminal 3, Departures Hall", "24/7", R.drawable.global_exchange_tlv)
    )


    fun getExchangePointsByCurrency(currencyCode: String): List<ExchangePoint> {
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

