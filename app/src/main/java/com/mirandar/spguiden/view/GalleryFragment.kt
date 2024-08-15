package com.mirandar.spguiden.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils
import com.mirandar.spguiden.model.GalleryAdapter

class GalleryFragment (
    private val context: Activity
) : Fragment()
{


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        val utils = Utils(context)
        val recyclerView: RecyclerView = view.findViewById(R.id.gallery_recycler)
        val adapter = GalleryAdapter(context, utils.getImg())
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = adapter




        return view
    }


}