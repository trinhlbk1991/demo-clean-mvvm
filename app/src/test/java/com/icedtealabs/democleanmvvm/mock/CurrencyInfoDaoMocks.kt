package com.icedtealabs.democleanmvvm.mock

import com.icedtealabs.democleanmvvm.data.local.dao.CurrencyInfoDao
import com.icedtealabs.democleanmvvm.data.local.entities.CurrencyInfoEntity
import com.icedtealabs.democleanmvvm.data.remote.CryptoCurrencyService
import com.icedtealabs.democleanmvvm.data.remote.response.CurrencyInfoResponse
import io.reactivex.Single
import org.mockito.kotlin.whenever

internal fun generateCurrencyInfoEntities(size: Int): List<CurrencyInfoEntity> {
    return (1..size).map {
        CurrencyInfoEntity(id = "$it", name = "Name: $it", symbol = "Symbol: $it")
    }
}

internal fun CurrencyInfoDao.mockGetAll(
    value: List<CurrencyInfoEntity> = emptyList()
) {
    whenever(getAll()).thenReturn(value)
}