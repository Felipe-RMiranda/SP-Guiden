package com.mirandar.spguiden.model

import android.app.Activity
import android.util.Log
import com.mirandar.spguiden.R
import java.io.IOException
import java.io.Serializable

class Data(private val context: Activity) : Serializable {
    init {
        log("Start DATA")
    }
    private val TAG = "** ***LOG_TO_SP*** **"

    val assetManager = context.assets

    fun loadImgsCarousel(): List<String> {
        log("Start LoadImg")
        val imagePaths = mutableListOf<String>()
        try {
            val files = assetManager.list("carousel_img")
            if (files != null) {
                for (file in files) {
                    imagePaths.add("carousel_img/$file")
                }
                log("FilePaths successfully Loaded")
            }
        }catch (e: IOException) {
            e.printStackTrace()
            log("FilePaths failed")
        }
        return imagePaths
    }
    fun loadImgsLocations(): List<String>{
        val imagePaths = mutableListOf<String>()
        try {
            val files = assetManager.list("locations")
            if (files != null) {
                for (file in files) {
                    imagePaths.add("locations/$file")
                }
            }
        }catch (e: IOException) {
            e.printStackTrace()
            log("FilePaths locations: failed")
        }
        return imagePaths
    }

    //Storage Locations
    val listObjLocation: List<ObjLocation> = listOf(
        ObjLocation(
            R.drawable.teatro,
            "Teatro Municipal de São Paulo",
            "Em 12 de setembro de 1911, com a ópera de Hamlet, de Ambrósio Thomas, era inaugurado um dos mais belos espaços culturais da cidade de São Paulo. O Theatro Municipal embalava os sonhos de uma cidade que crescia com a indústria e o café.",
            "null",
            "https://maps.app.goo.gl/2onykDSaHoQfgSEPA",
            "https://theatromunicipal.org.br/pt-br/"
        ),
        ObjLocation(
            R.drawable.museu_catavento,
            "Museu Catavento",
            "O Museu Catavento é um museu interativo, inaugurado em 2009 com o propósito de se dedicar às ciências e sua divulgação e está localizado no Palácio das Indústrias, em São Paulo, Brasil.",
            "https://www.instagram.com/museucatavento?igsh=OHk4enlmMnlpdWJk",
            "https://maps.app.goo.gl/SWkmdSWJe2u4T8vJ8",
            "http://www.museucatavento.org.br/"
        ),
        ObjLocation(
            R.drawable.sala_sp,
            "Sala São Paulo",
            "A Sala São Paulo é uma sala de concertos onde ocorrem apresentações sinfônicas e de câmara. Faz parte do Complexo Cultural Júlio Prestes, na antiga Estação Júlio Prestes, uma histórica estação ferroviária.",
            "null",
            "https://maps.app.goo.gl/pjnpoQCvRWqDNqib8",
            "https://salasaopaulo.art.br/salasp/pt/"
        )
    )
    private val linkInsta = "https://www.instagram.com/fellpe_r?igsh=b3h0dmdpOWszOTlw"
    private val linkGit = "https://github.com/Fellpe-R"
    private val linkLin = "https://www.linkedin.com/in/fellpe-ramos?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app"
    private val linkEmail = "mailto:fellpe.ramos@outlook.com"

    fun getLinkInsta() : String {
        return linkInsta
    }
    fun getLinkGit(): String{
        return linkGit
    }
    fun getLinkLin(): String{
        return linkLin
    }
    fun getLinkEmail(): String{
        return linkEmail
    }

    private fun log(s:String){
        Log.d(TAG, s)
    }
}