package com.example.exchangelocator

import android.content.Context


fun Context.getDeviceLanguage(): String {
    val deviceLanguage = this.resources.configuration.locale.language
    return deviceLanguage
}