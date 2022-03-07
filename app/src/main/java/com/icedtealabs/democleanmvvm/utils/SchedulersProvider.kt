package com.icedtealabs.democleanmvvm.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface SchedulersProvider {
    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler
}

class SchedulersProviderImpl @Inject constructor() : SchedulersProvider {

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }
}

