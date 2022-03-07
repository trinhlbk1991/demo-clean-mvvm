package com.icedtealabs.democleanmvvm.utils

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun Completable.then(source: () -> Completable): Completable {
    return this.andThen(source())
}

fun <T> Completable.thenSingle(source: () -> Single<T>): Single<T> {
    return this.andThen(source())
}

fun <T> Completable.thenMaybe(source: () -> Maybe<T>): Maybe<T> {
    return this.andThen(source())
}

fun <T> Completable.thenObservable(source: () -> Observable<T>): Observable<T> {
    return this.andThen(source())
}
