package com.icedtealabs.democleanmvvm.di

import com.icedtealabs.democleanmvvm.usecase.LoadCurrencyInfoListUseCase
import com.icedtealabs.democleanmvvm.usecase.LoadCurrencyInfoListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class UseCaseModule {

    @Binds
    abstract fun loadCurrencyInfoListUseCase(impl: LoadCurrencyInfoListUseCaseImpl): LoadCurrencyInfoListUseCase

}