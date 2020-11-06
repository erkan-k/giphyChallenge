package com.example.giphychallenge.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.giphychallenge.R
import com.example.giphychallenge.databinding.ActivityImageInfoBinding
import com.example.giphychallenge.databinding.ActivityMainBinding
import com.example.giphychallenge.model.GiphyInfoData
import com.example.giphychallenge.utils.IMAGE_INFO

class GiphyImageInfoActivity: AppCompatActivity() {

    private lateinit var binding: ActivityImageInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val imageInfo = intent.getParcelableExtra<GiphyInfoData>(IMAGE_INFO)
        binding.imageInfo = imageInfo
        Glide.with(binding.imageHolderInfo)
            .asGif()
            .placeholder(R.drawable.ic_file_image)
            .fallback(R.drawable.ic_image_broken_variant)
            .load(imageInfo?.url)
            .into(binding.imageHolderInfo)

    }
}