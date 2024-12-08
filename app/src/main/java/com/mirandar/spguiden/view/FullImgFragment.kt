package com.mirandar.spguiden.view

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
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
        return inflater.inflate(R.layout.full_img_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        utils = Utils(context)
        containerImg = view.findViewById(R.id.img_view)
        bitMap(position)
        var i = imgs.indexOf(position)
        val next: ImageView = view.findViewById(R.id.btn_next)
        val back: ImageView = view.findViewById(R.id.btn_back)
        val close = view.findViewById<ImageView>(R.id.close)
        val menu = view.findViewById<ImageView>(R.id.menu)

        for (im in imgs) {
            val i = imgs.indexOf(im)
            utils!!.log("Index: $i, Objeto: $im")
        }

        menu.setOnClickListener{ v ->
            showMenu(v)
        }

        close.setOnClickListener{
            activity?.finish()
        }

        next.setOnClickListener{
            if (i >= imgs.size-1){
                i = 0
            } else {
                i++
            }
            utils!!.log("Index: $i")
            bitMap(imgs[i])
        }
        back.setOnClickListener{
            if (i <= 0){
                i = imgs.size -1
            } else {
                i--
            }
            utils!!.log("Index: $i")
            bitMap(imgs[i])
        }
    }

    private fun showMenu(v: View){
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(R.menu.menu_navigation, popup.menu)
        popup.setOnMenuItemClickListener {menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.mnHome -> {
                    val close = Intent("close")
                    activity?.sendBroadcast(close)
                    true
                }
                R.id.mnAlbum -> {
                    activity?.finish()
                    true
                }
                R.id.mnLocations -> {
                    utils!!.showPopupWindow("locations")
                    activity?.finish()
                    true
                }
                R.id.mnAbout -> {
                    utils!!.showPopupWindow("About")
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun bitMap(s: String){
        val inputStream: InputStream = context.assets.open(s)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        containerImg!!.setImageBitmap(bitmap)
    }
    var utils: Utils? = null
    var containerImg: ImageView? = null
}