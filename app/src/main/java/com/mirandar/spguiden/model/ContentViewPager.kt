package com.mirandar.spguiden.model

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mirandar.spguiden.tabs.CulinariaFragment
import com.mirandar.spguiden.tabs.ParquesFragment
import com.mirandar.spguiden.tabs.PontosTuristicosFragment

class ContentViewPager(private val context: Activity, fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3 // Número de abas
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CulinariaFragment(context)
            1 -> ParquesFragment(context)
            2 -> PontosTuristicosFragment(context)
            else -> CulinariaFragment(context)
        }
    }
}