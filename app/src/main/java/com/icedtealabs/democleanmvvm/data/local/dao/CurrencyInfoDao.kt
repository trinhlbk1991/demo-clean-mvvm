package com.icedtealabs.democleanmvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.icedtealabs.democleanmvvm.data.local.entities.CurrencyInfoEntity

@Dao
interface CurrencyInfoDao : BaseDao<CurrencyInfoEntity> {

    @Query("SELECT * FROM currency_info")
    fun getAll(): List<CurrencyInfoEntity>

}