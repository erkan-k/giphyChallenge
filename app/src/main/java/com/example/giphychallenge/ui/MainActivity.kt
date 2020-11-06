package com.example.giphychallenge.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.giphychallenge.adapter.GiphyImageAdapter
import com.example.giphychallenge.databinding.ActivityMainBinding
import com.example.giphychallenge.retrofit.models.Data
import com.example.giphychallenge.viewmodel.TrendingViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TrendingViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GiphyImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(TrendingViewModel::class.java)
        recyclerView = binding.trendingImagesList

        if (viewModel.imageList == null) {
            viewModel.onTrendingResult.observe(this, androidx.lifecycle.Observer {
                if (it != null) {
                    setUpRecyclerView(it.data)
                }
            })
            viewModel.fetchTrendingImages {
                Toast.makeText(this, "Sorry, could not fetch images", Toast.LENGTH_LONG).show()
            }
        }else
            setUpRecyclerView(viewModel.imageList!!)
    }

    private fun setUpRecyclerView(data: List<Data>) {
        adapter = GiphyImageAdapter(data, this)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL )
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dispose()
    }
}