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
}