package com.icedtealabs.democleanmvvm.features

import com.icedtealabs.democleanmvvm.base.BaseViewModel
import com.icedtealabs.democleanmvvm.usecase.LoadCurrencyInfoListUseCase
import com.icedtealabs.democleanmvvm.utils.SchedulersProvider
import com.icedtealabs.democleanmvvm.utils.addTo
import com.icedtealabs.democleanmvvm.utils.withDefaultSchedulers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DemoViewModel @Inject constructor(
    private val loadCurrencyListUseCase: LoadCurrencyInfoListUseCase,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel<DemoIntent, DemoState, DemoEvent>() {

    init {
        setState(DemoState.initState())
    }

    override fun handleIntent(intent: DemoIntent) {
        when (intent) {
            is DemoIntent.Load -> loadData(intent.forceRefresh)
            DemoIntent.Sort -> sortData()
        }
    }


    private fun loadData(forceRefresh: Boolean) {
        loadCurrencyListUseCase.execute(forceRefresh)
            .withDefaultSchedulers(schedulersProvider)
            .doOnSubscribe {
                sendEvent(DemoEvent.ShowLoading)
            }
            .doOnTerminate {
                sendEvent(DemoEvent.HideLoading)
            }
            .subscribe({ currencies ->
                updateState { it.copy(currencies = currencies) }
            }, {
                sendEvent(DemoEvent.ShowError(it))
            })
            .addTo(disposables)
    }

    private fun sortData() {
        val currentState = states.value ?: return
        val newSort = if (currentState.sort == SortOrder.ASC) SortOrder.DESC else SortOrder.ASC

        updateState { it.copy(sort = newSort) }
    }

}