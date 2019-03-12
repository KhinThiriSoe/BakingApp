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
        private const val EXTRA_STEP_LIST = "extra_step_list"
        private const val EXTRA_STEP_POSITION = "extra_step_position"

        fun createIntent(context: Context, stepList: ArrayList<Step>, position: Int): Intent {
            return Intent(context, StepsActivity::class.java)
                .putExtra(StepsActivity.EXTRA_STEP_LIST, stepList)
                .putExtra(EXTRA_STEP_POSITION, position)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        initView()
    }

    private fun initView() {

        val steps = intent.getParcelableArrayListExtra<Step>(EXTRA_STEP_LIST)
        val position = intent.getIntExtra(EXTRA_STEP_POSITION, 0)

        if (supportActionBar != null) {
            supportActionBar?.title = resources.getString(R.string.steps)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        pagerAdapter = StepPagerAdapter(supportFragmentManager, steps)

        viewPager.adapter = pagerAdapter
        viewPager.currentItem = steps[position].id
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
