package com.example.exchangelocator.Data.models.Local_DB
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangelocator.Data.models.CoinDetail

@Database(entities = arrayOf(CoinDetail::class), version = 1, exportSchema = false)
abstract class  CoinDB : RoomDatabase() {
    abstract fun getCoinDao(): CoinDao
    companion object{
        @Volatile
        private var instance : CoinDB? = null

        fun getDatabase(context: Context) = instance?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, CoinDB::class.java, "coin_db")
                .allowMainThreadQueries().build()
        }
    }

}