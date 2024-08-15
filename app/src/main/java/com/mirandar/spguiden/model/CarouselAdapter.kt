package com.mirandar.spguiden.model

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import java.io.InputStream

class CarouselAdapter(
    private val context: Activity,
    private val imgs: List<String>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.imgContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val inputStream: InputStream = context.assets.open(imgs[position])
        val drawable = Drawable.createFromStream(inputStream, null).also { inputStream.close() }
        holder.imgView.setImageDrawable(drawable)
    }

    override fun getItemCount(): Int {
        return imgs.size
    }
}