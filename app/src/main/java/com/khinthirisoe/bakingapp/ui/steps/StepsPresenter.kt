package com.khinthirisoe.bakingapp.ui.steps

import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayerImpl

class StepsPresenter(steps: StepsContract.View) : StepsContract.Presenter {

    private val mediaPlayer = MediaPlayerImpl()

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()
}