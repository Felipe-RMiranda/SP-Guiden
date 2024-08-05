package com.mirandar.spguiden.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mirandar.spguiden.R
import java.io.InputStream

class ImgFragment(
    private val position: String,
    private val imgs: List<String>,
    private val context: Context
) : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.img_fragment, container, false)
        val inputStream: InputStream = context.assets.open(position)
        val container: ImageView = view.findViewById(R.id.img_view)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        container.setImageBitmap(bitmap)

        return view
    }

}