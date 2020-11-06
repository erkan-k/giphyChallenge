package com.example.giphychallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GiphyInfoData(val title: String, val source: String, val rating: String, val url: String) : Parcelable