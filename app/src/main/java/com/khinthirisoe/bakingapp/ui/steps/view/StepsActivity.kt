package com.khinthirisoe.bakingapp.ui.steps.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step


class StepsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_STEP = "extra_step"
        const val EXTRA_STEP_LIST = "extra_step_list"
    }

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: StepPagerAdapter
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        setUpView()
    }

    private fun setUpView() {
        val steps = intent.getParcelableArrayListExtra<Step>(EXTRA_STEP_LIST)
        val step = intent.getParcelableExtra<Step>(EXTRA_STEP)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        pagerAdapter = StepPagerAdapter(supportFragmentManager, steps)

        viewPager.adapter = pagerAdapter
        viewPager.currentItem = step.id
        tabLayout.setupWithViewPager(viewPager)

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
