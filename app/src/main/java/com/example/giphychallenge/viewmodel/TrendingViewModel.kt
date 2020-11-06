package com.example.giphychallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphychallenge.retrofit.models.TrendingImagesResponse
import com.example.giphychallenge.retrofit.GiphyRetrofit
import com.example.giphychallenge.retrofit.models.Data
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TrendingViewModel: ViewModel() {
    private val giphyService = GiphyRetrofit.imageService
    private lateinit var disposable: Disposable
    private var _trendingResult = MutableLiveData<TrendingImagesResponse>()
    var imageList: List<Data>? = null
    val onTrendingResult
        get() = _trendingResult

    fun fetchTrendingImages(onFail: () -> Unit) {
        disposable =
            giphyService.fetchTrendingImaged(GiphyRetrofit.apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { onFail() }
                .subscribe(
                    { result ->
                        _trendingResult.postValue(result)
                        imageList = result.data
                    },
                    {
                        _trendingResult.postValue(null)
                    }
                )
    }

    fun dispose(){
        disposable.dispose()
    }
}