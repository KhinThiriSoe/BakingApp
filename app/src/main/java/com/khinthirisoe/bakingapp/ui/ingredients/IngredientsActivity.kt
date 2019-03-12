package com.khinthirisoe.bakingapp.ui.ingredients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Recipe
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity
import com.khinthirisoe.bakingapp.ui.steps.StepsFragment

class IngredientsActivity : AppCompatActivity(), IngredientsFragment.OnFragmentInteractionListener {

    companion object {
        const val EXTRA_BAKING = "extra_baking"

        fun createIntent(context: Context, recipe: Recipe): Intent {
            return Intent(context, IngredientsActivity::class.java).putExtra(EXTRA_BAKING, recipe)
        }
    }

    private var fragmentContainer: FrameLayout? = null
    private var stepsFragment: StepsFragment? = null
    private var preferencesHelper: AppPreferencesHelper? = null

    private var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredients)

        preferencesHelper = AppPreferencesHelper(this)

        if (intent.hasExtra(EXTRA_BAKING)) {
            recipe = intent.getParcelableExtra<Recipe>(EXTRA_BAKING)
        }

        initView()
    }

    private fun initView() {

        if (supportActionBar != null) {
            supportActionBar?.title = recipe?.name
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

        supportFragmentManager.findFragmentById(R.id.list_selection_fragment) as IngredientsFragment

        fragmentContainer = findViewById(R.id.fragment_container)
        preferencesHelper!!.isLargeScreen = fragmentContainer != null
    }

    override fun onListItemClicked(position: Int) {

        if (!preferencesHelper!!.isLargeScreen) {
            startActivity(
                Intent(this, StepsActivity::class.java)
                    .putParcelableArrayListExtra(StepsActivity.EXTRA_STEP_LIST, recipe?.steps)
                    .putExtra(StepsActivity.EXTRA_STEP_POSITION, position)
            )

        } else {
            stepsFragment = StepsFragment.newInstance(recipe!!.steps, position)
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    stepsFragment!!,
                    "Steps Fragment"
                )
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}