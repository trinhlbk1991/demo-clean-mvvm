package com.icedtealabs.democleanmvvm.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: Observer<T>) {
    this.observe(owner) { value ->
        value?.let { observer.onChanged(it) }
    }
}
