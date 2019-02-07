package com.khinthirisoe.bakingapp.ui.steps.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ui.PlayerView
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.ui.steps.StepsContract
import com.khinthirisoe.bakingapp.ui.steps.presenter.StepsPresenter


class StepVideoFragment : Fragment(), StepsContract.View {

    companion object {
        const val EXTRA_STEP = "extra_step"

        fun newInstance(step: Step, position: Int): StepVideoFragment {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_STEP, step)
            bundle.putInt("position", position)
            val f = StepVideoFragment()
            f.arguments = bundle
            return f
        }
    }

    private var presenter: StepsContract.Presenter? = null
    private lateinit var videoView: PlayerView
    private lateinit var descriptionView: TextView

    private var step: Step? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter = StepsPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_step_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        videoView = view.findViewById(R.id.ep_video_view)
        descriptionView = view.findViewById(R.id.txt_description)

        step = arguments!!.getParcelable(EXTRA_STEP)
        val position = arguments!!.getInt("position")

        setUpToolbar(position)

        videoView.player = presenter!!.getPlayer().getPlayerImpl(context!!)
        presenter!!.play(step?.videoURL!!)
        descriptionView.text = step?.description
    }

    private fun setUpToolbar(position: Int) {

        if ((activity as AppCompatActivity).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            Log.d("message", position.toString())
            (activity as AppCompatActivity).supportActionBar?.title = "Step $position"
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter!!.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter!!.releasePlayer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDetachView()
    }
}