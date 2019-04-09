package com.khinthirisoe.bakingapp

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.khinthirisoe.bakingapp.ui.baking.view.BakingActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BakingActivityTest{

    @Rule
    var activityTestRule = ActivityTestRule(BakingActivity::class.java, false, false)


    @Test
    fun testClickOnAnyBakingItem() {

        val intent = Intent()
        activityTestRule.launchActivity(intent)
    }
}