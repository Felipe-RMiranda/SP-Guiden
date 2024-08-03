package com.mirandar.spguiden.model

import android.app.Activity
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R

class CarouselAdapter(private val context: Activity, private val imgs: List<String>) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgView: ImageView = itemView.findViewById(R.id.imgContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carousel_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val assetManager = context.assets
        val inputStream = assetManager.open(imgs[position])
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val recyclerView: RecyclerView = context.findViewById(R.id.recyclerContent)
        holder.imgView.setImageBitmap(bitmap)

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable{
            var i = 0
            override fun run() {
                if (i == imgs.size+1) {
                    i = 0
                }
                recyclerView.smoothScrollToPosition(i++)
                //handler.postDelayed(this, 3000)
            }
        }
        handler.post(runnable)
    }

    override fun getItemCount(): Int {
        return imgs.size
    }
}