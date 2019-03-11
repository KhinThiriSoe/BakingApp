package com.khinthirisoe.bakingapp.ui.steps

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.khinthirisoe.bakingapp.data.model.Step

class StepPagerAdapter(fragmentManager: FragmentManager, private var steps: ArrayList<Step>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return StepVideoFragment.newInstance(steps[position])
    }

    override fun getCount(): Int {
        return steps.size
    }
}
