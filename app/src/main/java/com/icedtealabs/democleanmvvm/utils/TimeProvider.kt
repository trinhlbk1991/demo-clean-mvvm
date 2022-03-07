package com.icedtealabs.democleanmvvm.utils

interface TimeProvider {
    fun getCurrentTimeInMillisecs(): Long
}

internal class TimeProviderImpl : TimeProvider {

    override fun getCurrentTimeInMillisecs(): Long {
        return System.currentTimeMillis()
    }

}