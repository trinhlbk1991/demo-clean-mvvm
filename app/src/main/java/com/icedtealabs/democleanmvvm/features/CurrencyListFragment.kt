package com.icedtealabs.democleanmvvm.features

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.icedtealabs.democleanmvvm.base.BaseFragment
import com.icedtealabs.democleanmvvm.databinding.FragmentCurrencyListBinding
import com.icedtealabs.democleanmvvm.models.CurrencyInfo

class CurrencyListFragment : BaseFragment() {

    private lateinit var binding: FragmentCurrencyListBinding
    private lateinit var currenciesAdapter: CurrenciesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context !is Listener) {
            throw IllegalStateException("Host activity must implement CurrencyListFragment#Listener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    fun updateCurrencies(currencies: List<CurrencyInfo>) {
        currenciesAdapter.submitList(currencies)
    }

    private fun setupViews() {
        currenciesAdapter = CurrenciesAdapter(context as Listener)
        with(binding) {
            rvCurrencies.adapter = currenciesAdapter
            rvCurrencies.layoutManager = LinearLayoutManager(requireContext())
        }

        val currencies =
            arguments?.getParcelableArrayList<CurrencyInfo>(ARG_CURRENCY_LIST) ?: arrayListOf()
        if (currencies.isNotEmpty()) {
            updateCurrencies(currencies)
        }
    }

    interface Listener {
        fun onCurrencyItemClicked(currency: CurrencyInfo)
    }

    companion object {
        const val TAG = "CurrencyListFragment"
        private const val ARG_CURRENCY_LIST = "currency_list"

        fun newInstance(currencyList: List<CurrencyInfo>): CurrencyListFragment {
            val bundle = Bundle().apply {
                putParcelableArrayList(ARG_CURRENCY_LIST, arrayListOf(*currencyList.toTypedArray()))
            }

            return CurrencyListFragment()
                .apply { arguments = bundle }
        }
    }
}