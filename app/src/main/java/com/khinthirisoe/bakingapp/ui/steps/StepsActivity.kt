package com.khinthirisoe.bakingapp.ui.steps

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ui.PlayerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step

class StepsActivity : AppCompatActivity(), StepsContract.View {

    companion object {
        const val EXTRA_STEP = "extra_step"
    }

    private lateinit var videoView: PlayerView
    private lateinit var descriptionView: TextView
    private lateinit var presenter: StepsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_steps)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.deactivate()
    }

    private fun init() {
        presenter = StepsPresenter(this)
        videoView = findViewById(R.id.ep_video_view)
        descriptionView = findViewById(R.id.txt_description)

        val step = intent.getParcelableExtra<Step>(EXTRA_STEP)
        videoView.player = presenter.getPlayer().getPlayerImpl(this)
        presenter.play(step.videoURL!!)
        descriptionView.text = step.description
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter.releasePlayer()
        }
    }
}
