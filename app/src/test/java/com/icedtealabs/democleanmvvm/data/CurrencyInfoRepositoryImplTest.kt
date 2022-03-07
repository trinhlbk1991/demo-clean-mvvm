package com.icedtealabs.democleanmvvm.data

import com.icedtealabs.democleanmvvm.data.local.dao.CurrencyInfoDao
import com.icedtealabs.democleanmvvm.data.remote.CryptoCurrencyService
import com.icedtealabs.democleanmvvm.mock.*
import com.icedtealabs.democleanmvvm.utils.TimeProvider
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class CurrencyInfoRepositoryImplTest {

    private val currencyInfoDao: CurrencyInfoDao = mock()
    private val currencyInfoService: CryptoCurrencyService = mock()
    private val timeProvider: TimeProvider = mock()

    private val repository = CurrencyInfoRepositoryImpl(
        currencyInfoDao,
        currencyInfoService,
        timeProvider
    )


    @Before
    fun setUp() {
        timeProvider.mockGetCurrentTime(100_000)
        currencyInfoService.mockGetCurrencyInfoList(generateCurrencyInfoResponse(size = 3))
        currencyInfoDao.mockGetAll(generateCurrencyInfoEntities(size = 4))
    }

    @After
    fun tearDown() {
        repository.lastTimeFetched = 0
    }

    @Test
    fun `get all with force fetch remote should fetch from api and update local db`() {
        repository.getAll(forceFetchRemote = true)
            .test()
            .assertValue { actual -> actual.size == 3 }
            .assertComplete()

        verify(currencyInfoService).getCurrencyInfoList()
        currencyInfoDao.verifyUpdateDataLocally(size = 3)
    }

    @Test
    fun `get all when exceed fetch interval should fetch from api`() {
        repository.getAll(forceFetchRemote = false)
            .test()
            .assertValue { actual -> actual.size == 3 }
            .assertComplete()

        verify(currencyInfoService).getCurrencyInfoList()
        currencyInfoDao.verifyUpdateDataLocally(size = 3)
    }

    @Test
    fun `get all when no local data should fetch from api`() {
        currencyInfoDao.mockGetAll(value = emptyList())

        repository.getAll(forceFetchRemote = false)
            .test()
            .assertValue { actual -> actual.size == 3 }
            .assertComplete()

        verify(currencyInfoService).getCurrencyInfoList()
        currencyInfoDao.verifyUpdateDataLocally(size = 3)
    }

    @Test
    fun `get all when not exceed fetch interval should load from local db`() {
        repository.lastTimeFetched = 99_000

        repository.getAll(forceFetchRemote = false)
            .test()
            .assertValue { actual -> actual.size == 4 }
            .assertComplete()

        verifyNoInteractions(currencyInfoService)
    }

    private fun CurrencyInfoDao.verifyUpdateDataLocally(size: Int) {
        verify(this).deleteAll()
        verify(this, times(size)).insert(any())
    }
}
