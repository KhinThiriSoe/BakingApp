package com.khinthirisoe.bakingapp

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.khinthirisoe.bakingapp.ui.baking.view.BakingActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BakingActivityTest{

    @get:Rule
    val activityTestRule = ActivityTestRule(BakingActivity::class.java)

    @Before
    fun launchActivity() {
        ActivityScenario.launch(BakingActivity::class.java)
    }

    @Test
    fun testClickOnAnyBakingItem() {

    }
}