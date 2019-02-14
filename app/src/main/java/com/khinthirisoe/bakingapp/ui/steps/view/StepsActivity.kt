package com.khinthirisoe.bakingapp.ui.steps.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R


class StepsActivity : AppCompatActivity() {

    private var stepsFragment: StepsFragment = StepsFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        supportFragmentManager.findFragmentById(R.id.list_selection_fragment) as StepsFragment

        setUpToolbar()

    }

    private fun setUpToolbar() {

        if (supportActionBar != null) {
            supportActionBar?.title = "Steps"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
