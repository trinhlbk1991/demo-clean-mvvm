package com.icedtealabs.democleanmvvm.base

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<I : Intent, S : State, E : Event> : ViewModel() {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    private val _states: MutableLiveData<S> = MutableLiveData()
    val states: LiveData<S> = _states

    private val _events: MutableLiveData<E> = MutableLiveData()
    val events: LiveData<E> = _events

    private val intents: MutableLiveData<I> = MutableLiveData()
    private val intentsObserver = Observer<I> {
        Log.i(TAG, "Handle intent: $it")
        handleIntent(it)
    }

    init {
        intents.observeForever(intentsObserver)
    }

    fun sendIntent(intent: I) {
        intents.value = intent
    }

    protected abstract fun handleIntent(intent: I)

    @UiThread
    protected fun setState(state: S) {
        Log.i(TAG, "Set state: $state")
        _states.value = state
    }

    @UiThread
    protected fun sendEvent(event: E) {
        Log.i(TAG, "Send event: $event")
        _events.value = event
    }

    override fun onCleared() {
        super.onCleared()
        intents.removeObserver(intentsObserver)
        disposables.dispose()
    }

    companion object {
        private const val TAG = "BaseViewModel"
    }
}

interface State
interface Event
interface Intent