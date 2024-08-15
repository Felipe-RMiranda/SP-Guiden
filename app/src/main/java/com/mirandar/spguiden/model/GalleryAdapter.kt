package com.mirandar.spguiden.model

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils
import java.io.InputStream

class GalleryAdapter(
    private val context: Activity,
    private val imgs: List<String>
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.galleryImage)
        val utils = Utils(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ViewHolder, position: Int) {
        val input = holder.utils.createThumbnail(imgs[position])
        holder.img.setImageDrawable(input)
        holder.img.setOnClickListener{
        holder.utils.showPopupWindow("imgView", imgs[position])
        }
    }

    override fun getItemCount(): Int {
        return imgs.size
    }

}