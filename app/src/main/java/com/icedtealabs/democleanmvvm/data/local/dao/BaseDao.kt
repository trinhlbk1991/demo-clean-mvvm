package com.icedtealabs.democleanmvvm.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update


interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg entity: T): List<Long>

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(entity: T)

}

@Transaction
fun <T> BaseDao<T>.upsert(entity: T) {
    if (insert(entity) == -1L) {
        update(entity)
    }
}

@Transaction
fun <T> BaseDao<T>.upsert(entities: List<T>) {
    entities.forEach { entity ->
        upsert(entity)
    }
}