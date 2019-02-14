package com.khinthirisoe.bakingapp.ui.steps.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.view.StepsFragment.Companion.EXTRA_STEP
import com.khinthirisoe.bakingapp.ui.steps.view.StepsFragment.Companion.EXTRA_STEP_LIST


class StepsActivity : AppCompatActivity() {

    private var stepsFragment: StepsFragment? = null
    private var stepList: ArrayList<Step>? = null
    private var step: Step? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        if (intent.hasExtra(EXTRA_STEP_LIST)) {
            stepList = intent.getParcelableArrayListExtra(EXTRA_STEP_LIST)
            step = intent.getParcelableExtra(EXTRA_STEP)
        }

        stepsFragment = StepsFragment.newInstance(stepList!!, step!!)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, stepsFragment!!, "Step Fragment")
            .commit()

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
