package com.icedtealabs.democleanmvvm.base

import android.util.Log
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseActivity : AppCompatActivity() {

    protected fun showError(messageId: Int) {
        showError(getString(messageId))
    }

    protected fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun currentFragmentTag(@IdRes container: Int): String? {
        val currentFragment = supportFragmentManager.findFragmentById(container)
        return currentFragment?.tag
    }

    protected fun setFragment(
        @IdRes container: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        title = ""

        if (tag == currentFragmentTag(container)) {
            return
        }

        try {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(container, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag)
            }
            fragmentTransaction.commit()
        } catch (ex: Exception) {
            Log.e(TAG, "Set fragment failed ", ex)
        }
    }

    companion object {
        private const val TAG = "BaseActivity"
    }
}