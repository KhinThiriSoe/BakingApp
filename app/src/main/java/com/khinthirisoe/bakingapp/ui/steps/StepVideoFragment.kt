package com.khinthirisoe.bakingapp.ui.steps

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_step_video.*


class StepVideoFragment : Fragment() {

    private lateinit var exoPlayer: ExoPlayer

    companion object {
        const val STEP_ID = "id"
        const val STEP_DESCRIPTION = "description"
        const val STEP_SHORT_DESCRIPTION = "short_description"
        const val STEP_VIDEO = "video"

        fun newInstance(step: Step): StepVideoFragment {

            val args = Bundle()
            args.putInt(STEP_ID, step.id)
            args.putString(STEP_DESCRIPTION, step.description)
            args.putString(STEP_SHORT_DESCRIPTION, step.shortDescription)
            args.putString(STEP_VIDEO, step.videoURL)

            val fragment = StepVideoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_step_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        val args = arguments
        val description = args?.getString(STEP_DESCRIPTION)
        val shortDescription = args?.getString(STEP_SHORT_DESCRIPTION)
        val videoUrl = args?.getString(STEP_VIDEO)

        initializePlayer()

        ep_video_view.player = getPlayer()
        play(videoUrl!!)
        txt_description.text = description
        txt_short_description.text = shortDescription
    }

    private fun initializePlayer() {

        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val rendererFactory = DefaultRenderersFactory(context)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(rendererFactory, trackSelector, loadControl)
    }

    private fun getPlayer(): ExoPlayer {
        initializePlayer()
        return exoPlayer
    }

    private fun play(url: String) {
        val userAgent = Util.getUserAgent(context, getString(R.string.app_name))
        val mediaSource = ExtractorMediaSource
            .Factory(DefaultDataSourceFactory(context, userAgent))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(url))
        exoPlayer.prepare(mediaSource)
        exoPlayer.playWhenReady = true
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