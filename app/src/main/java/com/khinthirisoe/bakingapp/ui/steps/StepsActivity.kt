package com.khinthirisoe.bakingapp.ui.steps

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity() {

    private lateinit var exoPlayer: ExoPlayer

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

        val videoUrl = intent.getParcelableExtra<Step>(EXTRA_STEP)
        ep_video_view.player = getPlayer()
        play(videoUrl.videoURL!!)
    }

    private fun play(url: String) {
        val userAgent = Util.getUserAgent(this, getString(R.string.app_name))
        val mediaSource = ExtractorMediaSource
            .Factory(DefaultDataSourceFactory(this, userAgent))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(url))
        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
    }

    private fun getPlayer(): ExoPlayer {
        initializePlayer()
        return exoPlayer
    }

    private fun initializePlayer() {

        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val rendererFactory = DefaultRenderersFactory(this)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(rendererFactory, trackSelector, loadControl)
    }

    private fun releasePlayer() {
        exoPlayer.stop()
        exoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            releasePlayer()
        }
    }

}
