package com.icedtealabs.democleanmvvm.di

import com.icedtealabs.democleanmvvm.data.CurrencyInfoRepository
import com.icedtealabs.democleanmvvm.data.CurrencyInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun currencyInfoRepository(impl: CurrencyInfoRepositoryImpl): CurrencyInfoRepository

}