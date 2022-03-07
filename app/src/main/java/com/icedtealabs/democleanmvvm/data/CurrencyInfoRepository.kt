package com.icedtealabs.democleanmvvm.data

import androidx.annotation.VisibleForTesting
import com.icedtealabs.democleanmvvm.data.local.dao.CurrencyInfoDao
import com.icedtealabs.democleanmvvm.data.local.dao.upsert
import com.icedtealabs.democleanmvvm.data.mapper.toEntity
import com.icedtealabs.democleanmvvm.data.mapper.toModel
import com.icedtealabs.democleanmvvm.data.remote.CryptoCurrencyService
import com.icedtealabs.democleanmvvm.models.CurrencyInfo
import com.icedtealabs.democleanmvvm.utils.TimeProvider
import io.reactivex.Single
import javax.inject.Inject

interface CurrencyInfoRepository {
    fun getAll(forceFetchRemote: Boolean): Single<List<CurrencyInfo>>
}

internal class CurrencyInfoRepositoryImpl @Inject constructor(
    private val currencyInfoDao: CurrencyInfoDao,
    private val currencyInfoService: CryptoCurrencyService,
    private val timeProvider: TimeProvider,
) : CurrencyInfoRepository {

    @VisibleForTesting
    var lastTimeFetched: Long = 0

    override fun getAll(forceFetchRemote: Boolean): Single<List<CurrencyInfo>> {
        return Single.fromCallable { currencyInfoDao.getAll() }
            .flatMap { localEntities ->
                if (localEntities.isEmpty() || exceedFetchInterval()) {
                    fetchCurrencyInfoList()
                } else {
                    Single.just(localEntities.map { it.toModel() })
                }
            }

    }

    private fun exceedFetchInterval() =
        timeProvider.getCurrentTimeInMillisecs() - lastTimeFetched > FETCH_INTERVAL

    private fun fetchCurrencyInfoList(): Single<List<CurrencyInfo>> {
        return currencyInfoService.getCurrencyInfoList()
            .doOnSuccess { response ->
                val entities = response.map { it.toEntity() }
                currencyInfoDao.deleteAll()
                currencyInfoDao.upsert(entities)

                lastTimeFetched = timeProvider.getCurrentTimeInMillisecs()
            }
            .map { response ->
                response.map { it.toModel() }
            }
    }

    companion object {
        private const val FETCH_INTERVAL = 30_000
    }
}