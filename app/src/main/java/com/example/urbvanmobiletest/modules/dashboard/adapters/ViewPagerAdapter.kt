package com.example.urbvanmobiletest.modules.dashboard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.urbvanmobiletest.modules.receiver.views.ReceiverFragment
import com.example.urbvanmobiletest.modules.transmitter.views.TransmitterFragment



class ViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val COUNT = 2

    private var titles = ArrayList<String>()

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> TransmitterFragment()
            else -> ReceiverFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        titles.add("Enviar")
        titles.add("Mostrar")
        return titles[position]
    }

    override fun getCount(): Int {
        return COUNT
    }
}