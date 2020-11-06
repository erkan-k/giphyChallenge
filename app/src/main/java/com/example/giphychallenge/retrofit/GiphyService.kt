package com.example.giphychallenge.retrofit

import com.example.giphychallenge.retrofit.models.TrendingImagesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {
    @GET("trending")
    fun fetchTrendingImaged(@Query("api_key") apiKey: String): Observable<TrendingImagesResponse>
}