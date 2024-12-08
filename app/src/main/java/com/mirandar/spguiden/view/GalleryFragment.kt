package com.mirandar.spguiden.view

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.ProgressBar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.control.Utils
import com.mirandar.spguiden.model.GalleryAdapter

class GalleryFragment (
    private val context: Activity
) : Fragment()
{

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gallery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val content = view.findViewById<LinearLayout>(R.id.content)
        val recyclerView: RecyclerView = view.findViewById(R.id.gallery_recycler)
        val menu = view.findViewById<ImageView>(R.id.menu)
        val close = view.findViewById<ImageView>(R.id.close)

        menu.setOnClickListener{ v ->
            showMenu(v)
        }

        close.setOnClickListener{
            activity?.finish()
        }

        content.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        val thread = Thread {
            utils = Utils(context)
            val img = utils!!.getImg()

            context.runOnUiThread{
                val adapter = GalleryAdapter(context, img)
                recyclerView.layoutManager = GridLayoutManager(context, 3)
                recyclerView.adapter = adapter
            }
        }
        thread.start()

        Thread {
            try {
                thread.join()
                context.runOnUiThread{
                    progressBar.visibility = View.GONE
                    content.visibility = View.VISIBLE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun showMenu(v: View){
        val popup = PopupMenu(context, v)
        popup.menuInflater.inflate(R.menu.menu_navigation, popup.menu)
        popup.setOnMenuItemClickListener {menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.mnHome -> {
                    activity?.finish()
                    true
                }
                R.id.mnAlbum -> {
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

    var utils: Utils? = null
}