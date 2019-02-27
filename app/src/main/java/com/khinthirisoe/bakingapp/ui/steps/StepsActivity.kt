package com.khinthirisoe.bakingapp.ui.steps

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import kotlinx.android.synthetic.main.activity_steps.*

class StepsActivity : AppCompatActivity(), StepsContract.View {

    companion object {
        const val EXTRA_STEP = "extra_step"
    }

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

        val videoUrl = intent.getParcelableExtra<Step>(EXTRA_STEP)
        ep_video_view.player = presenter.getPlayer().getPlayerImpl(this)
        presenter.play(videoUrl.videoURL!!)
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
