package com.mirandar.spguiden.model

import android.app.Activity
import android.util.Log
import java.io.IOException

class Data(private val context: Activity) {
    init {
        log("Start DATA")
    }
    private val TAG = "** ***LOG_TO_SP*** **"


    fun loadImgs(): List<String> {
        log("Start LoadImg")
        val assetManager =context.assets
        val imagePaths = mutableListOf<String>()
        try {
            val files = assetManager.list("Carrocel_imgs")
            if (files != null) {
                for (file in files) {
                    imagePaths.add("Carrocel_imgs/$file")
                }
                log("FilePaths successfully Loaded")
            }
        }catch (e: IOException) {
            e.printStackTrace()
            log("FilePaths failed")
        }
        return imagePaths
    }

    private fun log(s:String){
        Log.d(TAG, s)
    }
}