package com.mirandar.spguiden.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        utils = Utils(this)
        val btnMenuHome: ImageView = findViewById(R.id.btnMenu)
        btnMenuHome.setOnClickListener{ view ->
            showMenu(view)
        }
        utils!!.printImgs()
        utils!!.startCarousel()
        utils!!.localList()
    }

    private fun showMenu(v: View){
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(R.menu.menu_navigation, popup.menu)
        popup.setOnMenuItemClickListener {menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.mnHome -> {
                    val scroll = findViewById<NestedScrollView>(R.id.scroll)
                    scroll.smoothScrollTo(0, 0)
                    true
                }
                R.id.mnAlbum -> {
                    utils!!.showPopupWindow("img")
                    true
                }
                R.id.mnLocations -> {
                    utils!!.showPopupWindow("locations")
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

    override fun onPause() {
        super.onPause()
        utils!!.pauseRunnable()
    }

    override fun onResume() {
        super.onResume()
        utils!!.startRunnable()
    }
    private var utils: Utils? = null
}