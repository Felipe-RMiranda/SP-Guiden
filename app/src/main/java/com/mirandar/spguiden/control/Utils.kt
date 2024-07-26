package com.mirandar.spguiden.control

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mirandar.spguiden.R
import com.mirandar.spguiden.model.CarouselAdapter
import com.mirandar.spguiden.model.Data

class Utils(private val context: Activity) {
    init {
        log("Start Utils")
    }

    private val TAG = "** ***LOG_TO_SP*** **"
    private var data: Data = Data(context)
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarouselAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val img = data.loadImgs()

    fun startCarousel() {
        log("Start Carousel")
        context.runOnUiThread{
            recyclerView = context.findViewById(R.id.recyclerContent)
            adapter = CarouselAdapter(context, img)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
            for (img in img){
                log("Img: $img")
            }
            handler = Handler(Looper.getMainLooper())
            runnable = object : Runnable{
                var currentPosition = 0
                override fun run() {
                    if (currentPosition == img.size) {
                        currentPosition = 0
                        log("currentPosition = 0")
                    }
                    log("Start Looping")
                    recyclerView.smoothScrollToPosition(currentPosition++)
                    handler.postDelayed(this, 3000)
                }
            }
            handler.post(runnable)
        }
    }

    private fun log(s:String){
        Log.d(TAG, s)
    }
}