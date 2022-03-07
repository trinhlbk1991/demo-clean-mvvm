package com.icedtealabs.democleanmvvm.utils

import javax.inject.Inject

interface TimeProvider {
    fun getCurrentTimeInMillisecs(): Long
}

internal class TimeProviderImpl @Inject constructor() : TimeProvider {

    override fun getCurrentTimeInMillisecs(): Long {
        return System.currentTimeMillis()
    }

}