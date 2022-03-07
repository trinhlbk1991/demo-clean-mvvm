package com.icedtealabs.democleanmvvm.di

import com.icedtealabs.democleanmvvm.utils.SchedulersProvider
import com.icedtealabs.democleanmvvm.utils.SchedulersProviderImpl
import com.icedtealabs.democleanmvvm.utils.TimeProvider
import com.icedtealabs.democleanmvvm.utils.TimeProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppUtilsModule {

    @Binds
    abstract fun timeProvider(impl: TimeProviderImpl): TimeProvider

    @Binds
    abstract fun schedulersProvider(impl: SchedulersProviderImpl): SchedulersProvider
}