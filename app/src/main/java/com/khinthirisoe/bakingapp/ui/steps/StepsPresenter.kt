package com.khinthirisoe.bakingapp.ui.steps

import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayerImpl
import java.lang.ref.WeakReference

class StepsPresenter(steps: StepsContract.View) : StepsContract.Presenter {

    private val mediaPlayer = MediaPlayerImpl()

    private val view = WeakReference(steps)

    override fun deactivate() {
    }

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()
}