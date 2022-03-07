package com.icedtealabs.democleanmvvm.data.mapper

import com.icedtealabs.democleanmvvm.data.local.entities.CurrencyInfoEntity
import com.icedtealabs.democleanmvvm.data.remote.response.CurrencyInfoResponse
import com.icedtealabs.democleanmvvm.models.CurrencyInfo

internal fun CurrencyInfoEntity.toModel() = CurrencyInfo(id, name, symbol)

internal fun CurrencyInfoResponse.toEntity() = CurrencyInfoEntity(id, name, symbol)

internal fun CurrencyInfoResponse.toModel() = CurrencyInfo(id, name, symbol)