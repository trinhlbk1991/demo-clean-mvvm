package com.icedtealabs.democleanmvvm.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.icedtealabs.democleanmvvm.databinding.ItemCurrencyBinding
import com.icedtealabs.democleanmvvm.models.CurrencyInfo

class CurrenciesAdapter(
    val listener: CurrencyListFragment.Listener
) : ListAdapter<CurrencyInfo, CurrenciesAdapter.CurrencyVH>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return CurrencyVH(binding)
    }

    override fun onBindViewHolder(holder: CurrencyVH, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class CurrencyVH(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyInfo, listener: CurrencyListFragment.Listener) {
            with(binding) {
                tvIcon.text = "${currency.symbol.first()}"
                tvName.text = currency.name
                tvSymbol.text = currency.symbol

                root.setOnClickListener { listener.onCurrencyItemClicked(currency) }
            }
        }

    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrencyInfo>() {
            override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
                return oldItem == newItem
            }

        }
    }
}