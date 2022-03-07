package com.icedtealabs.democleanmvvm.di

import android.content.Context
import com.icedtealabs.democleanmvvm.data.local.CryptoCurrencyDatabase
import com.icedtealabs.democleanmvvm.data.local.dao.CurrencyInfoDao
import com.icedtealabs.democleanmvvm.data.remote.CryptoCurrencyMockService
import com.icedtealabs.democleanmvvm.data.remote.CryptoCurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    fun bucketDao(@ApplicationContext context: Context): CurrencyInfoDao {
        return CryptoCurrencyDatabase.getInstance(context).currencyInfoDao()
    }

    @Provides
    fun cryptoCurrencyService(@ApplicationContext context: Context): CryptoCurrencyService {
        return CryptoCurrencyMockService(context)
    }

}