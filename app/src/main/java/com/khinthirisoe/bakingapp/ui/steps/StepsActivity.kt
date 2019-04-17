package com.khinthirisoe.bakingapp.ui.steps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step

class StepsActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_STEP_LIST = "extra_step_list"
        const val EXTRA_STEP_POSITION = "extra_step_position"

        fun createIntent(context: Context, stepList: ArrayList<Step>, position: Int): Intent {
            return Intent(context, StepsActivity::class.java)
                .putExtra(StepsActivity.EXTRA_STEP_LIST, stepList)
                .putExtra(EXTRA_STEP_POSITION, position)
        }

    }

    private var stepsFragment: StepsFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        if (intent.hasExtra(EXTRA_STEP_LIST)) {
            val stepList = intent.getParcelableArrayListExtra<Step>(EXTRA_STEP_LIST)
            val position = intent.getIntExtra(EXTRA_STEP_POSITION, 0)

            stepsFragment = StepsFragment.newInstance(stepList!!, position)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, stepsFragment!!, "Step Fragment")
                .commit()

        }

        initView()
    }

    private fun initView() {

        if (supportActionBar != null) {
            supportActionBar?.title = getString(R.string.steps)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}