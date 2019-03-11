package com.khinthirisoe.bakingapp.ui.steps

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
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

        fun newInstance(step: Step): StepVideoFragment {
            return StepVideoFragment().apply {
                arguments = bundleOf(Step::class.java.simpleName to step)
            }
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
        val step = args?.getParcelable<Step>(Step::class.java.simpleName)
        val description = step?.description
        val shortDescription = step?.shortDescription
        val videoUrl = step?.videoURL

        initializePlayer()

        play(videoUrl!!)
        txt_description.text = description
        txt_short_description.text = shortDescription
    }

    private fun initializePlayer() {

        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val rendererFactory = DefaultRenderersFactory(context)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(rendererFactory, trackSelector, loadControl)

        ep_video_view.player = exoPlayer
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