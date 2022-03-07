package com.icedtealabs.democleanmvvm.data.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icedtealabs.democleanmvvm.data.remote.response.CurrencyInfoResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject


internal class CryptoCurrencyMockService @Inject constructor(
    @ApplicationContext private val context: Context
) : CryptoCurrencyService {

    override fun getCurrencyInfoList(): Single<List<CurrencyInfoResponse>> {
        return Single.fromCallable {
            try {
                Thread.sleep(3000) // Simulate network call

                val inputStream: InputStream = context.assets.open("data.json")
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                val json = String(buffer)
                val type: TypeToken<List<CurrencyInfoResponse>> =
                    object : TypeToken<List<CurrencyInfoResponse>>() {}
                Gson().fromJson(json, type.type)
            } catch (e: IOException) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

}