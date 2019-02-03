package com.khinthirisoe.bakingapp.ui.ingredients

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.data.model.ReceipeResponse



class IngredientsActivity : AppCompatActivity() {

    private lateinit var receipeName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.khinthirisoe.bakingapp.R.layout.activity_ingredients)

        val data = intent.getParcelableExtra<ReceipeResponse>("data")
        receipeName = data.name

        setUpToolbar()
    }

    private fun setUpToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.title = receipeName
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
