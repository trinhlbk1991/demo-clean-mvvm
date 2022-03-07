package com.icedtealabs.democleanmvvm.di

import android.content.Context
import com.icedtealabs.democleanmvvm.data.local.CryptoCurrencyDatabase
import com.icedtealabs.democleanmvvm.data.local.dao.CurrencyInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun bucketDao(context: Context): CurrencyInfoDao {
        return CryptoCurrencyDatabase.getInstance(context).currencyInfoDao()
    }

}