package com.icedtealabs.democleanmvvm.usecase

import com.icedtealabs.democleanmvvm.data.CurrencyInfoRepository
import com.icedtealabs.democleanmvvm.models.CurrencyInfo
import io.reactivex.Single
import javax.inject.Inject

interface LoadCurrencyInfoListUseCase {
    fun execute(forceFetchRemote: Boolean = false): Single<List<CurrencyInfo>>
}

internal class LoadCurrencyInfoListUseCaseImpl @Inject constructor(
    private val currencyInfoRepository: CurrencyInfoRepository
) : LoadCurrencyInfoListUseCase {

    override fun execute(forceFetchRemote: Boolean): Single<List<CurrencyInfo>> {
        return currencyInfoRepository.getAll(forceFetchRemote)
    }
}