package com.khinthirisoe.bakingapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.di.App
import com.khinthirisoe.bakingapp.di.component.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponent(App.appComponent)
    }

    protected abstract fun setupComponent(appComponent: AppComponent)
}
