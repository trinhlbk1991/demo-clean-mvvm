package com.icedtealabs.democleanmvvm.data.remote

import com.icedtealabs.democleanmvvm.data.remote.response.CurrencyInfoResponse
import io.reactivex.Single

internal interface CryptoCurrencyService {
    fun getCurrencyInfoList(): Single<List<CurrencyInfoResponse>>
}