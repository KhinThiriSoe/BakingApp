package com.khinthirisoe.bakingapp.ui.steps

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayerImpl
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity() {

    private lateinit var mediaPlayerImpl: MediaPlayerImpl

    companion object {
        const val EXTRA_STEP = "extra_step"

        fun createIntent(context: Context, step: Step): Intent {
            return Intent(context, StepsActivity::class.java).putExtra(StepsActivity.EXTRA_STEP, step)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)

        initView()
    }

    private fun initView() {

        mediaPlayerImpl = MediaPlayerImpl()

        val videoUrl = intent.getParcelableExtra<Step>(EXTRA_STEP)
        ep_video_view.player = mediaPlayerImpl.getPlayerImpl(this)
        mediaPlayerImpl.play(videoUrl.videoURL!!)
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mediaPlayerImpl.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaPlayerImpl.releasePlayer()
        }
    }

}
