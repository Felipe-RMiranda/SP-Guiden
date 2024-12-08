package com.mirandar.spguiden.model

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils

class LocationsAdapter(
    private val context: Activity,
    private val itemList: List<ObjLocation>
) : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    val utils = Utils(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.local_item, parent, false)
        return ViewHolder(itemView)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgCapa: ImageView = itemView.findViewById(R.id.imgCapa)
        val title: TextView = itemView.findViewById(R.id.alertTitle)
        val description: TextView = itemView.findViewById(R.id.description)
        val linkInstagram: ImageView = itemView.findViewById(R.id.instagram)
        val linkMaps: ImageView = itemView.findViewById(R.id.maps)
        val linkWeb: ImageView = itemView.findViewById(R.id.web)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listLocations = itemList[position]
        holder.imgCapa.setImageResource(listLocations.getImgCapa())
        holder.title.setText(listLocations.getTitle())
        holder.description.setText(listLocations.getDesc())
        holder.linkInstagram.setImageResource(R.drawable.insta)
        holder.linkMaps.setImageResource(R.drawable.maps)
        holder.linkWeb.setImageResource(R.drawable.web)

        holder.linkInstagram.setOnClickListener{
            web(listLocations.getLinkInst())
        }
        holder.linkMaps.setOnClickListener{
            web(listLocations.getLinkMaps())
        }
        holder.linkWeb.setOnClickListener{
            web(listLocations.getLinkWeb())
        }
    }

    private fun web(s:String){
        if (s != "null"){
            utils.openWebPage(s)
        }else{
            utils.message("Página indisponível!")
        }
    }

    override fun getItemCount() = itemList.size
}