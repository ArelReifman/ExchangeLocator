package com.example.exchangelocator.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangelocator.models.CoinDetail
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CoinDetail::class], version = 2, exportSchema = false)
abstract class CoinDB : RoomDatabase() {
    abstract fun getCoinDao(): CoinDao

    companion object {
        @Volatile
        private var instance: CoinDB? = null

        fun getDatabase(context: Context) = instance ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, CoinDB::class.java, "coin_db")
                .addMigrations(MIGRATION_1_2)
                .build().also {
                    instance = it
                }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE coinsTable ADD COLUMN imageUri TEXT DEFAULT NULL")
            }
        }
    }

}