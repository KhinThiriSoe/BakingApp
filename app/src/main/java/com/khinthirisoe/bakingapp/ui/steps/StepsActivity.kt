package com.khinthirisoe.bakingapp.ui.steps

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step

class StepsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        val step = intent.getParcelableExtra<Step>("step")
        Toast.makeText(this, step.description, Toast.LENGTH_SHORT).show()
    }
}
