package com.example.giphychallenge.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GiphyRetrofit {

    const val apiKey = "Jb2EPF1b8iCr8ifVwyPdkMJfKlDDju1A"

    val imageService by lazy {
        create()
    }

    private fun create(): GiphyService {

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create())
            .baseUrl("https://api.giphy.com/v1/gifs/")
            .build()

        return retrofit.create(GiphyService::class.java)
    }
}