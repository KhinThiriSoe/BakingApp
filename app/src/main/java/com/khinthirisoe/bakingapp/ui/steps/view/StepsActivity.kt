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
    }

    private fun setUpView() {
        viewPager = findViewById(R.id.view_pager)

        val steps = intent.getParcelableArrayListExtra<Step>(EXTRA_STEP_LIST)
        val step = intent.getParcelableExtra<Step>(EXTRA_STEP)

        val adapter = StatePageAdapter(supportFragmentManager, steps)
        viewPager!!.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
