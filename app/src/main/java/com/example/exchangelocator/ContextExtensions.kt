package com.example.exchangelocator

import android.content.Context
import androidx.core.os.ConfigurationCompat


fun Context.getDeviceLanguage(): String =
    ConfigurationCompat.getLocales(resources.configuration)
        .get(0)
        ?.language
        ?: "en"
