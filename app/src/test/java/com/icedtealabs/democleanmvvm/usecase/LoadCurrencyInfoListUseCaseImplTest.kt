package com.icedtealabs.democleanmvvm.usecase

import com.icedtealabs.democleanmvvm.data.CurrencyInfoRepository
import io.reactivex.Single
import org.junit.Test
import org.mockito.kotlin.*

class LoadCurrencyInfoListUseCaseImplTest {
    private val currencyInfoRepository: CurrencyInfoRepository = mock()
    private val useCase = LoadCurrencyInfoListUseCaseImpl(currencyInfoRepository)

    @Test
    fun `execute  success`() {
        whenever(currencyInfoRepository.getAll(any())).thenReturn(Single.just(emptyList()))

        useCase.execute(forceFetchRemote = true)
            .test()
            .assertValue { actual -> actual.isEmpty() }
            .assertComplete()

        verify(currencyInfoRepository).getAll(eq(true))
    }
}