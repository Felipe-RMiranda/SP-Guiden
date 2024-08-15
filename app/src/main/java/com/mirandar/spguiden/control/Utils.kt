package com.mirandar.spguiden.control

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import java.io.InputStream

class Utils(private val context: Activity) {
    init {
        log("Start Utils")
//        printImgs()
    }

    private val TAG = "** ***LOG_TO_SP*** **"
    private var data: Data = Data(context)
    private lateinit var recyclerView: RecyclerView
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var locationAdapter: LocationsAdapter
    private var carouselRunning = false
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val img = data.loadImgsCarousel()
    private  val locationsImg = data.loadImgsLocations()

    fun getImg(): List<String>{
        return img
    }

    fun printImgs() {
        for (im in img) {
            val i = img.indexOf(im)
            log("Index: $i, Objeto: $im")
        }
    }

    fun getData(): Data{
        return data
    }

    fun startCarousel() {
        carouselRunning = true
        log("Start Carousel")
        context.runOnUiThread {
            recyclerView = context.findViewById(R.id.recyclerContent)
            carouselAdapter = CarouselAdapter(context, img)
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = carouselAdapter
        }
        val recyclerView: RecyclerView = context.findViewById(R.id.recyclerContent)
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            var i = 0
            override fun run() {
                if (i == img.size) {
                    i = 0
                } else {
                    i++
                }
                Log.d("LOG", "$i")
                recyclerView.smoothScrollToPosition(i)
                handler.postDelayed(this, 5000)
            }
        }
        handler.post(runnable)
    }

    fun pauseRunnable(){
        handler.removeCallbacks(runnable)
        carouselRunning = false
    }
    fun startRunnable(){
        if (!carouselRunning) {
            handler.post(runnable)
        }
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

    fun createThumbnail(input: String): Drawable {
        val inputStream: InputStream = context.assets.open(input)
        val originalBitmap = BitmapFactory.decodeStream(inputStream)
        val scale = 0.1
        val width = (originalBitmap.width * scale).toInt()
        val height = (originalBitmap.height * scale).toInt()
        val thumbnailBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, false)
        return BitmapDrawable(context.resources, thumbnailBitmap)
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