package com.khinthirisoe.bakingapp.ui.steps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: StepPagerAdapter

    companion object {
        const val EXTRA_STEP = "extra_step"
        private const val EXTRA_STEP_LIST = "extra_step_list"

        fun createIntent(context: Context, step: Step, stepList: ArrayList<Step>): Intent {
            return Intent(context, StepsActivity::class.java).putExtra(StepsActivity.EXTRA_STEP, step)
                .putExtra(StepsActivity.EXTRA_STEP_LIST, stepList)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        initView()
    }

    private fun initView() {

        val steps = intent.getParcelableArrayListExtra<Step>(EXTRA_STEP_LIST)
        val step = intent.getParcelableExtra<Step>(EXTRA_STEP)

        if (supportActionBar != null) {
            supportActionBar?.title = getString(R.string.steps)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        pagerAdapter = StepPagerAdapter(supportFragmentManager, steps)

        viewPager.adapter = pagerAdapter
        viewPager.currentItem = step.id
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
