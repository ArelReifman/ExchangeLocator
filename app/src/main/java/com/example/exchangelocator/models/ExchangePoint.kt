package com.example.exchangelocator.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExchangePoint(
    val id: Int,
    val name: String,
    val country: String,
    val city: String,
    val street: String,
    val openingHours: String,
    val imageResId: Int,
    val customImagePath: String? = null
) : Parcelable