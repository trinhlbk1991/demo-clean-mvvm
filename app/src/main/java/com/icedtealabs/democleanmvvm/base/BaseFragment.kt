package com.icedtealabs.democleanmvvm.base

import android.widget.Toast
import androidx.fragment.app.Fragment

class BaseFragment : Fragment() {

    protected fun showError(messageId: Int) {
        showError(getString(messageId))
    }

    protected fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}