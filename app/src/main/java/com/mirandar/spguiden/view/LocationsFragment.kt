package com.mirandar.spguiden.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mirandar.spguiden.R
import com.mirandar.spguiden.model.ContentViewPager

class LocationsFragment(
    private val context: Activity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.locations_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.contentTabs)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        val contentViewPager = ContentViewPager(context, this)
        viewPager.adapter = contentViewPager

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Culinária"
                1 -> "Parques"
                2 -> "Turismo"
                else -> null
            }
        }.attach()
    }
}