package com.zeltixdev.photoapp.models

data class Photo(
    val author: String,
    val download_url: String,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)