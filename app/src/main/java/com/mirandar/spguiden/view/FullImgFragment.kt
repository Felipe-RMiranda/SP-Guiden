package com.mirandar.spguiden.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils
import java.io.InputStream

class FullImgFragment(
    private val position: String,
    private val imgs: List<String>,
    private val context: Activity
) : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.full_img_fragment, container, false)
        containerImg = view.findViewById(R.id.img_view)
        bitMap(position)
        var i = imgs.indexOf(position)
        val next: ImageView = view.findViewById(R.id.btn_next)
        val back: ImageView = view.findViewById(R.id.btn_back)
        next.setOnClickListener{
            if (i >= imgs.size){
                i = 0
            } else {
                i++
            }
            bitMap(imgs[i])
        }
        back.setOnClickListener{
            if (i <= 0){
                i = imgs.size
            } else {
                i--
            }
            bitMap(imgs[i])
        }
        return view
    }

    private fun bitMap(s: String){
        val inputStream: InputStream = context.assets.open(s)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        containerImg!!.setImageBitmap(bitmap)
    }

    var containerImg: ImageView? = null
}