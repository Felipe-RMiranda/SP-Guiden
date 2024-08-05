package com.mirandar.spguiden.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils

class PopupWindow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.popup_window)
        val utils = Utils(this)
        var imgView : ImgFragment? = null


        val view = intent.getStringExtra("Fragment")
        val imgsPosition = intent.getStringExtra(("position"))
        val img = GalleryFragment(this)
        if (imgsPosition != null) {
            imgView = ImgFragment(imgsPosition!!, utils.getImg(), this)
        } else {
            utils.log("Null! | imgsPosition: "+imgsPosition)
        }

        when (view) {
            "img" -> openFrag(img)
            "locations" -> openFrag(img)
            "About" -> openFrag(img)
            "imgView" -> openFrag(imgView!!)
            else -> utils.message("Página indisponível!")
        }

    }
    private fun openFrag(f: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, f).commit()
    }
}