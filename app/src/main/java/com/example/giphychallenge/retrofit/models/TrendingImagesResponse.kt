package com.example.giphychallenge.retrofit.models

data class TrendingImagesResponse(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)