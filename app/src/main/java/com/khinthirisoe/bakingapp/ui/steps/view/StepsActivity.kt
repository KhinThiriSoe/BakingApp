package com.khinthirisoe.bakingapp.ui.steps.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step


class StepsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STEP = "extra_step"
        const val EXTRA_STEP_LIST = "extra_step_list"
    }

    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        setUpView()

        setUpToolbar()

    }

    private fun setUpView() {
        viewPager = findViewById(R.id.view_pager)

        val steps = intent.getParcelableArrayListExtra<Step>(EXTRA_STEP_LIST)
        val adapter = StatePageAdapter(supportFragmentManager, steps)
        viewPager!!.adapter = adapter
    }

    private fun setUpToolbar() {

        val step = intent.getParcelableExtra<Step>(EXTRA_STEP)

        if (supportActionBar != null) {
            supportActionBar?.title = "Step " + (step.id + 1)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
