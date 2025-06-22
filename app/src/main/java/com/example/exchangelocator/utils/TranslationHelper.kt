package com.example.exchangelocator.utils

import android.content.Context
import com.example.exchangelocator.R

object TranslationHelper {

    fun getStringByKey(context: Context, key: String): String {
        val resourceId = when (key) {

            "name_cxi_currency_exchange" -> R.string.name_cxi_currency_exchange
            "name_travelex" -> R.string.name_travelex
            "name_travelex_germany" -> R.string.name_travelex_germany
            "name_travelex_france" -> R.string.name_travelex_france
            "name_ice_currency_exchange" -> R.string.name_ice_currency_exchange
            "name_gpa_currency_exchange" -> R.string.name_gpa_currency_exchange
            "name_mizuho_bank" -> R.string.name_mizuho_bank
            "name_change_place" -> R.string.name_change_place
            "name_global_exchange" -> R.string.name_global_exchange


            "country_usa" -> R.string.country_usa
            "country_uk" -> R.string.country_uk
            "country_germany" -> R.string.country_germany
            "country_france" -> R.string.country_france
            "country_japan" -> R.string.country_japan
            "country_israel" -> R.string.country_israel


            "city_new_york" -> R.string.city_new_york
            "city_london" -> R.string.city_london
            "city_frankfurt" -> R.string.city_frankfurt
            "city_paris" -> R.string.city_paris
            "city_tokyo" -> R.string.city_tokyo
            "city_tel_aviv" -> R.string.city_tel_aviv


            "address_jfk_terminal_8" -> R.string.address_jfk_terminal_8
            "address_jfk_terminal_4" -> R.string.address_jfk_terminal_4
            "address_heathrow_terminal_5" -> R.string.address_heathrow_terminal_5
            "address_heathrow_underground" -> R.string.address_heathrow_underground
            "address_frankfurt_terminal_1" -> R.string.address_frankfurt_terminal_1
            "address_charles_de_gaulle" -> R.string.address_charles_de_gaulle
            "address_narita_terminal_1" -> R.string.address_narita_terminal_1
            "address_haneda_terminal_3" -> R.string.address_haneda_terminal_3
            "address_ben_gurion_terminal_3" -> R.string.address_ben_gurion_terminal_3


            "hours_0600_2300" -> R.string.hours_0600_2300
            "hours_0600_2200" -> R.string.hours_0600_2200
            "hours_0500_2300" -> R.string.hours_0500_2300
            "hours_0730_2000" -> R.string.hours_0730_2000
            "hours_0530_2230" -> R.string.hours_0530_2230
            "hours_0630_2300" -> R.string.hours_0630_2300
            "hours_24_7" -> R.string.hours_24_7

            else -> return key
        }

        return context.getString(resourceId)
    }
}