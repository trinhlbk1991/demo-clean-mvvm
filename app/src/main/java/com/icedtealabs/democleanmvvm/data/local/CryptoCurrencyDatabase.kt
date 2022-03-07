package com.icedtealabs.democleanmvvm.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.icedtealabs.democleanmvvm.data.local.CryptoCurrencyDatabase.Companion.DB_VERSION
import com.icedtealabs.democleanmvvm.data.local.dao.CurrencyInfoDao
import com.icedtealabs.democleanmvvm.data.local.entities.CurrencyInfoEntity

@Database(
    entities = [
        CurrencyInfoEntity::class,
    ],
    version = DB_VERSION
)
internal abstract class CryptoCurrencyDatabase : RoomDatabase() {

    abstract fun currencyInfoDao(): CurrencyInfoDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "crypto_currency"

        private var instance: CryptoCurrencyDatabase? = null

        fun getInstance(context: Context): CryptoCurrencyDatabase {
            if (instance == null) {
                synchronized(CryptoCurrencyDatabase::class) {
                    if (instance == null) {
                        instance = androidx.room.Room.databaseBuilder(
                            context,
                            CryptoCurrencyDatabase::class.java,
                            DB_NAME
                        ).build()
                    }
                }
            }

            return instance!!
        }
    }

}