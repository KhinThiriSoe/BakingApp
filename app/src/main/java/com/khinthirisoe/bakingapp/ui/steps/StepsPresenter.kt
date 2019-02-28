package com.khinthirisoe.bakingapp.ui.steps

import com.khinthirisoe.bakingapp.ui.steps.player.MediaPlayerImpl

class StepsPresenter : StepsContract.Presenter {

    private var view: StepsContract.View? = null

    override fun onAttachView(view: StepsContract.View) {
        this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    private val mediaPlayer = MediaPlayerImpl()

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()
}