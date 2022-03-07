package com.icedtealabs.democleanmvvm.utils

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Completable.withDefaultSchedulers(schedulersProvider: SchedulersProvider): Completable {
    return this.subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}

fun <T> Single<T>.withDefaultSchedulers(schedulersProvider: SchedulersProvider): Single<T> {
    return this.subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}

fun <T> Observable<T>.withDefaultSchedulers(schedulersProvider: SchedulersProvider): Observable<T> {
    return this.subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}

fun <T> Maybe<T>.withDefaultSchedulers(schedulersProvider: SchedulersProvider): Maybe<T> {
    return this.subscribeOn(schedulersProvider.io())
        .observeOn(schedulersProvider.ui())
}

fun Disposable.addTo(disposables: CompositeDisposable) {
    disposables.add(this)
}