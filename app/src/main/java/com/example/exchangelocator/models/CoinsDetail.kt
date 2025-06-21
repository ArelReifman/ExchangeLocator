package com.example.exchangelocator.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "coinsTable")
data class CoinDetail(
    @ColumnInfo(name = "fromCurrency")
    val fromCurrency: String,

    @ColumnInfo(name = "toCurrency")
    val toCurrency: String,

    @ColumnInfo(name = "originalAmount")
    val originalAmount: Double,

    @ColumnInfo(name = "resultAmount")
    val resultAmount: Double,

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
) : Parcelable, java.io.Serializable




