package com.mirandar.spguiden.tabs

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils
import com.mirandar.spguiden.model.LocationsAdapter

class PontosTuristicosFragment(
    private val context: Activity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pontos_turisticos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val utils = Utils(context)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerTurismo)
        val locationAdapter = LocationsAdapter(context, utils.getData().listObjLocation)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = locationAdapter
    }
}