package com.icedtealabs.democleanmvvm.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icedtealabs.democleanmvvm.R
import com.icedtealabs.democleanmvvm.base.BaseActivity

class DemoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
    }

}