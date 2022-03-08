package com.icedtealabs.democleanmvvm.features

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.icedtealabs.democleanmvvm.R
import com.icedtealabs.democleanmvvm.base.BaseActivity
import com.icedtealabs.democleanmvvm.databinding.ActivityDemoBinding
import com.icedtealabs.democleanmvvm.models.CurrencyInfo
import com.icedtealabs.democleanmvvm.utils.observeNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DemoActivity : BaseActivity(), CurrencyListFragment.Listener {

    private lateinit var binding: ActivityDemoBinding
    private val viewModel: DemoViewModel by viewModels()

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDemoBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setupViews()
        setupObservers()

        if (savedInstanceState == null) {
            viewModel.sendIntent(DemoIntent.Load())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_demo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> viewModel.sendIntent(DemoIntent.Load(forceRefresh = true))
            R.id.action_sort -> viewModel.sendIntent(DemoIntent.Sort)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCurrencyItemClicked(currency: CurrencyInfo) {
        Toast.makeText(this, "Clicked: ${currency.name}", Toast.LENGTH_SHORT).show()
    }

    private fun setupObservers() {
        viewModel.states.observeNotNull(this) { state ->
            val fragment = currentFragment(R.id.container)
            if (fragment == null) {
                setFragment(
                    R.id.container,
                    CurrencyListFragment.newInstance(state.currencies),
                    CurrencyListFragment.TAG,
                    addToBackStack = false
                )
            } else {
                (fragment as? CurrencyListFragment)?.updateCurrencies(state.currencies)
            }
        }

        viewModel.events.observeNotNull(this) { event ->
            when (event) {
                DemoEvent.ShowLoading -> showProgress()
                DemoEvent.HideLoading -> hideProgress()
                is DemoEvent.ShowError -> showError(
                    event.exception.message ?: getString(R.string.msg_unknown_error)
                )
            }
        }
    }

    private fun hideProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    private fun showProgress() {
        progressDialog = ProgressDialog.show(this, null, getString(R.string.msg_loading_currencies))
    }

    private fun setupViews() {
        setSupportActionBar(binding.toolbar)
    }

    companion object {
        private const val TAG = "DemoActivity"
    }
}