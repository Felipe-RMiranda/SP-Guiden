package com.mirandar.spguiden.control

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.model.CarouselAdapter
import com.mirandar.spguiden.model.Data
import com.mirandar.spguiden.model.LocationsAdapter
import com.mirandar.spguiden.view.PopupWindow

class Utils(private val context: Activity) {
    init {
        log("Start Utils")
    }

    private val TAG = "** ***LOG_TO_SP*** **"
    private var data: Data = Data(context)
    private lateinit var recyclerView: RecyclerView
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var locationAdapter: LocationsAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val img = data.loadImgsCarousel()
    private  val locationsImg = data.loadImgsLocations()

    fun getImg(): List<String>{
        return img
    }

    fun pauseRunnable(){
        handler.removeCallbacks(runnable)
    }
    fun startRunnable(){
        handler.post(runnable)
    }

    fun startCarousel() {
        log("Start Carousel")
        context.runOnUiThread{
            recyclerView = context.findViewById(R.id.recyclerContent)
            carouselAdapter = CarouselAdapter(context, img)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = carouselAdapter
        }
        val recyclerView: RecyclerView = context.findViewById(R.id.recyclerContent)
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable{
            var i = 0
            override fun run() {
                if (i == recyclerView.adapter!!.itemCount) {
                    i = 0
                    recyclerView.scrollToPosition(i)
                }else{
                    i++
                }
                for (img in img) {

                    Log.d("LOG", ""+i)
                }
                recyclerView.smoothScrollToPosition(i)
                handler.postDelayed(this, 5000)
            }
        }
        handler.post(runnable)
    }

    fun localList() {
        context.runOnUiThread{
            recyclerView = context.findViewById(R.id.recyclerList)
            locationAdapter = LocationsAdapter(context, data.listObjLocation)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = locationAdapter
        }

    }
    @SuppressLint("QueryPermissionsNeeded")
    fun openWebPage(url:String){
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        context.startActivity(intent)
    }
    fun showPopupWindow(fragment: String, position: String? = null){
        val intent = Intent(context, PopupWindow::class.java)
        intent.putExtra("Fragment", fragment)
        intent.putExtra("position", position)
        intent.setType(Intent.ACTION_VIEW)
        context.startActivity(intent)
    }

    fun message(s:String){
        context.runOnUiThread{
            Toast.makeText(context, s, Toast.LENGTH_LONG).show()
        }
    }

    fun log(s:String){
        Log.d(TAG, s)
    }
}