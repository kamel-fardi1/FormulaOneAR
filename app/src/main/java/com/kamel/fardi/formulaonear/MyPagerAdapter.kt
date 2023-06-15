package com.kamel.fardi.formulaonear
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.kamel.fardi.formulaonear.View.Fragement.FragmentHome

class MyPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa){

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentHome()
            }
            else -> {
                return FragmentHome()
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

}