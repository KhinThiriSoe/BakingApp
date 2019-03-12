package com.khinthirisoe.bakingapp.ui.steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper
import com.khinthirisoe.bakingapp.ui.steps.StepsActivity.Companion.EXTRA_STEP_LIST
import kotlinx.android.synthetic.main.fragment_steps.*


class StepsFragment : Fragment() {

    companion object {

        fun newInstance(steps: ArrayList<Step>, position: Int): StepsFragment {
            return StepsFragment().apply {
                arguments =
                    bundleOf(
                        StepsActivity.EXTRA_STEP_LIST to steps,
                        StepsActivity.EXTRA_STEP_POSITION to position
                    )
            }
        }
    }

    private var pagerAdapter: StepPagerAdapter? = null
    private var preferencesHelper: AppPreferencesHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_steps, container, false)

        preferencesHelper = AppPreferencesHelper(context!!)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stepList = arguments!!.getParcelableArrayList<Step>(EXTRA_STEP_LIST)
        val position = arguments!!.getInt(StepsActivity.EXTRA_STEP_POSITION)

        if (preferencesHelper!!.isLargeScreen) {
            if (pagerAdapter == null) {
                pagerAdapter = StepPagerAdapter(childFragmentManager, stepList)
                viewPager.adapter = pagerAdapter
                viewPager.currentItem = stepList[position].id
            }

        } else {
            pagerAdapter = StepPagerAdapter(activity!!.supportFragmentManager, stepList)
            viewPager.adapter = pagerAdapter
            viewPager.currentItem = stepList[position].id
            tabLayout.setupWithViewPager(viewPager)

        }
    }
}
