package com.icedtealabs.democleanmvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.icedtealabs.democleanmvvm.data.local.entities.CurrencyInfoEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
internal interface CurrencyInfoDao : BaseDao<CurrencyInfoEntity> {

    @Query("SELECT * FROM currency_info")
    fun getAll(): List<CurrencyInfoEntity>

    @Query("DELETE FROM currency_info")
    fun deleteAll()

}