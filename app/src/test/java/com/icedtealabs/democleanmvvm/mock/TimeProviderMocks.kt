package com.icedtealabs.democleanmvvm.mock

import com.icedtealabs.democleanmvvm.utils.TimeProvider
import org.mockito.kotlin.whenever

fun TimeProvider.mockGetCurrentTime(value: Long) {
    whenever(getCurrentTimeInMillisecs()).thenReturn(value)
}