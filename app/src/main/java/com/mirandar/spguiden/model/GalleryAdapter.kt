package com.mirandar.spguiden.model

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils
import com.mirandar.spguiden.view.PopupWindow
import java.io.InputStream

class GalleryAdapter(
    private val context: Activity,
    private val imgs: List<String>
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val img: ImageView = view.findViewById(R.id.galleryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryAdapter.ViewHolder, position: Int) {
        val input = createThumbnail(imgs[position])
        holder.img.setImageDrawable(input)
        holder.img.setOnClickListener {
            showPopupWindow("imgView", imgs[position])
        }
    }

    override fun getItemCount(): Int {
        return imgs.size
    }

    fun createThumbnail(input: String): Drawable {
        val inputStream: InputStream = context.assets.open(input)
        val originalBitmap = BitmapFactory.decodeStream(inputStream)
        val scale = 0.1
        val width = (originalBitmap.width * scale).toInt()
        val height = (originalBitmap.height * scale).toInt()
        val thumbnailBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false)
        return BitmapDrawable(context.resources, thumbnailBitmap)
    }

    fun showPopupWindow(fragment: String, position: String? = null){
        val intent = Intent(context, PopupWindow::class.java)
        intent.putExtra("Fragment", fragment)
        intent.putExtra("position", position)
        intent.setType(Intent.ACTION_VIEW)
        context.startActivity(intent)
    }

}