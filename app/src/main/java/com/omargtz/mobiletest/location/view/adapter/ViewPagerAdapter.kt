package com.omargtz.mobiletest.location.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.omargtz.mobiletest.location.view.fragment.ReceiverFragment
import com.omargtz.mobiletest.location.view.fragment.TransmitterFragment


class ViewPagerAdapter(manager: FragmentManager, titles: List<String>): FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val COUNT = 2
    private val RECEIVER = 0
    private val TRANSMITTER = 1;
    private var titles = titles

    override fun getItem(position: Int): Fragment {
        return when (position) {
            RECEIVER -> TransmitterFragment()
            else -> ReceiverFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return titles.size
    }
}