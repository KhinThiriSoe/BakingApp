package com.khinthirisoe.bakingapp.ui.steps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.khinthirisoe.bakingapp.R
import com.khinthirisoe.bakingapp.data.model.Step
import com.khinthirisoe.bakingapp.data.prefs.AppPreferencesHelper


class StepsFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private var pagerAdapter: StepPagerAdapter? = null
    private lateinit var tabLayout: TabLayout

    private var preferencesHelper: AppPreferencesHelper? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_steps, container, false)

        preferencesHelper = AppPreferencesHelper(context!!)

        viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)


        val stepList = arguments!!.getParcelableArrayList<Step>(EXTRA_STEP_LIST)
        val step = arguments!!.getParcelable<Step>(EXTRA_STEP)

        if (preferencesHelper!!.isLargeScreen) {
            if (pagerAdapter == null) {
                pagerAdapter = StepPagerAdapter(childFragmentManager, stepList)
                viewPager.adapter = pagerAdapter
                viewPager.currentItem = step.id
            }

        } else {
            pagerAdapter = StepPagerAdapter(activity!!.supportFragmentManager, stepList)
            viewPager.adapter = pagerAdapter
            viewPager.currentItem = step.id
            tabLayout.setupWithViewPager(viewPager)

        }

        return view
    }

    companion object {

        const val EXTRA_STEP = "extra_step"
        const val EXTRA_STEP_LIST = "extra_step_list"

        @JvmStatic
        fun newInstance(list: ArrayList<Step>, step: Step): StepsFragment {
            val fragment = StepsFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_STEP, step)
            args.putParcelableArrayList(EXTRA_STEP_LIST, list)
            fragment.arguments = args
            return fragment
        }
    }
}
