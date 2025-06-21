package com.example.exchangelocator.data.local

import com.example.exchangelocator.R
import com.example.exchangelocator.models.ExchangePoint

object ExchangePointsConst {
    // Note: In a real app, the string resource IDs for name and hours would be stored directly.
    // For this example, we'll keep the structure but use R.string references.
    // The ExchangePoint model would need to be updated to accept Int for name and hours if we were to pass IDs directly.
    // However, the current ExchangePoint model expects String for name and openingHours.
    // So, these will be resolved to strings at runtime when ExchangeDetailsFragment calls getString().
    // This means ExchangePointsConst itself doesn't directly use getString(), but provides the keys.
    // The fragment will then use these string keys with getString().
    // For simplicity and to match the current structure, we'll keep country, city, street as direct string keys
    // as these are already handled by getString(R.string.<key>) in ExchangeDetailsFragment.
    // The names and hours will also be string keys.

    val exchangePoints = listOf(
        ExchangePoint(
            1,
            "ep_name_cxi_currency_exchange", // String key
            "country_usa", // String key
            "city_new_york", // String key
            "address_jfk_terminal_8", // String key
            "ep_hours_0600_2300", // String key
            R.drawable.cxi_us
        ),
        ExchangePoint(
            2,
            "ep_name_travelex", // String key
            "country_usa", // String key
            "city_new_york", // String key
            "address_jfk_terminal_4", // String key
            "ep_hours_0600_2200", // String key
            R.drawable.travelex_us
        ),
        ExchangePoint(
            3,
            "ep_name_travelex", // String key
            "country_uk", // String key
            "city_london", // String key
            "address_heathrow_terminal_5", // String key
            "ep_hours_0500_2300", // String key
            R.drawable.travelex_uk
        ),
        ExchangePoint(
            4,
            "ep_name_ice_currency_exchange", // String key
            "country_uk", // String key
            "city_london", // String key
            "address_heathrow_underground", // String key
            "ep_hours_0730_2000", // String key
            R.drawable.ice_uk
        ),
        ExchangePoint(
            5,
            "ep_name_travelex_germany", // String key
            "country_germany", // String key
            "city_frankfurt", // String key
            "address_frankfurt_terminal_1", // String key
            "ep_hours_0600_2200", // String key
            R.drawable.travelex_gr
        ),
        ExchangePoint(
            6,
            "ep_name_travelex_france", // String key
            "country_france", // String key
            "city_paris", // String key
            "address_charles_de_gaulle", // String key
            "ep_hours_0530_2230", // String key
            R.drawable.travelex_fr
        ),
        ExchangePoint(
            7,
            "ep_name_gpa_currency_exchange", // String key
            "country_japan", // String key
            "city_tokyo", // String key
            "address_narita_terminal_1", // String key
            "ep_hours_0630_2300", // String key
            R.drawable.gpa_tokyo
        ),
        ExchangePoint(
            8,
            "ep_name_mizuho_bank", // String key
            "country_japan", // String key
            "city_tokyo", // String key
            "address_haneda_terminal_3", // String key
            "ep_hours_24_7", // String key
            R.drawable.mizuho_bank_tokyo
        ),
        ExchangePoint(
            9,
            "ep_name_change_place", // String key
            "country_israel", // String key
            "city_tel_aviv", // String key
            "address_ben_gurion_terminal_3", // String key
            "ep_hours_24_7", // String key
            R.drawable.bank_hapoalim_tlv
        ),
        ExchangePoint(
            10,
            "ep_name_global_exchange", // String key
            "country_israel", // String key
            "city_tel_aviv", // String key
            "address_ben_gurion_terminal_3", // String key
            "ep_hours_24_7", // String key
            R.drawable.global_exchange_tlv
        )
    )
}