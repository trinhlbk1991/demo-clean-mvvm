package com.icedtealabs.democleanmvvm.mock

import com.icedtealabs.democleanmvvm.data.remote.CryptoCurrencyService
import com.icedtealabs.democleanmvvm.data.remote.response.CurrencyInfoResponse
import io.reactivex.Single
import org.mockito.kotlin.whenever

internal fun generateCurrencyInfoResponse(size: Int): List<CurrencyInfoResponse> {
    return (1..size).map {
        CurrencyInfoResponse(id = "$it", name = "Name: $it", symbol = "Symbol: $it")
    }
}

internal fun CryptoCurrencyService.mockGetCurrencyInfoList(
    value: List<CurrencyInfoResponse> = emptyList()
) {
    whenever(getCurrencyInfoList()).thenReturn(Single.just(value))
}