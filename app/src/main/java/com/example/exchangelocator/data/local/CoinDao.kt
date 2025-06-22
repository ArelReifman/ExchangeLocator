package com.example.exchangelocator.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.exchangelocator.models.CoinDetail

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoin(coin: CoinDetail)

    @Update
    suspend fun updateCoin(coin: CoinDetail)

    @Delete
    suspend fun deleteCoin(coin: CoinDetail)

    @Query("SELECT * FROM coinsTable ORDER BY fromCurrency")
    fun getAllCoins(): LiveData<List<CoinDetail>>
}