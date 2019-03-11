package com.khinthirisoe.bakingapp.ui.steps

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.khinthirisoe.bakingapp.data.model.Step

class StepPagerAdapter(fragmentManager: FragmentManager, private var stepList: ArrayList<Step>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return StepVideoFragment.newInstance(stepList[position])
    }

    override fun getCount(): Int {
        return stepList.size
    }
}
