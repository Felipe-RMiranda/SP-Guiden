package com.mirandar.spguiden.model

class ObjLocation(
    private val imgCapa: Int,
    private val title: String,
    private val description: String,
    private val linkInst: String,
    private val linkMaps: String,
    private val linkWeb: String
    ) {
    fun getImgCapa(): Int {
        return imgCapa
    }
    fun getTitle(): String{
        return title
    }
    fun getDesc(): String{
        return description
    }
    fun getLinkInst(): String{
        return linkInst
    }
    fun getLinkMaps(): String{
        return linkMaps
    }
    fun getLinkWeb(): String{
        return linkWeb
    }
}