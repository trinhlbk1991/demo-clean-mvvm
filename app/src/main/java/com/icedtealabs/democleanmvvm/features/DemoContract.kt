package com.icedtealabs.democleanmvvm.features

import com.icedtealabs.democleanmvvm.base.Event
import com.icedtealabs.democleanmvvm.base.Intent
import com.icedtealabs.democleanmvvm.base.State
import com.icedtealabs.democleanmvvm.models.CurrencyInfo

internal sealed class DemoIntent : Intent {
    class Load(val forceRefresh: Boolean = false) : DemoIntent()
    object Sort : DemoIntent()
}

internal data class DemoState(
    val currencies: List<CurrencyInfo> = emptyList(),
    val sort: SortOrder = SortOrder.ASC
) : State {
    companion object {
        fun initState() = DemoState()
    }
}

internal sealed class DemoEvent : Event {
    object ShowLoading : DemoEvent()
    object HideLoading : DemoEvent()
    class ShowError(val exception: Throwable) : DemoEvent()
}

enum class SortOrder {
    ASC,
    DESC
}