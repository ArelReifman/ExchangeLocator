package com.example.exchangelocator.Data.models.Local_DB
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.exchangelocator.Data.models.CoinDetail

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCoin(coin: CoinDetail)

    @Delete
    fun deleteCoin(coin: CoinDetail)

    @Update
    fun updateCoin(coin: CoinDetail)

    @Query("SELECT * FROM coinsTable ORDER BY fromCurrency")
    fun getAllCoins(): LiveData<List<CoinDetail>>

}