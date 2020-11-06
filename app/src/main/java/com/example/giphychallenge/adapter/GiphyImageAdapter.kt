package com.example.giphychallenge.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphychallenge.retrofit.models.Data
import com.example.giphychallenge.R
import com.example.giphychallenge.databinding.ImageListItemBinding
import com.example.giphychallenge.model.GiphyInfoData
import com.example.giphychallenge.ui.GiphyImageInfoActivity
import com.example.giphychallenge.utils.IMAGE_INFO
import java.util.*

class GiphyImageAdapter(private val images: List<Data>, private val context: Context) : RecyclerView.Adapter<GiphyImageAdapter.BindingHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageListItemBinding.inflate(inflater)
        return BindingHolder(binding)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int){
        holder.bind(images[position])
    }


    inner class BindingHolder constructor(private val binding: ImageListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data){
            with(binding){
                Glide.with(imageHolder)
                    .asGif()
                    .placeholder(R.drawable.ic_file_image)
                    .fallback(R.drawable.ic_image_broken_variant)
                    .load(item.images.downsized_large.url)
                    .into(imageHolder)
                imageHolder.setOnClickListener {
                    val intent = Intent(context, GiphyImageInfoActivity::class.java)
                    intent.putExtra(IMAGE_INFO, GiphyInfoData(
                        title = item.title,
                        source = "Title: " + if (item.source.isEmpty()) "Undefined Source" else "Source: " + item.source,
                        rating = "Rating: " + item.rating.toUpperCase(Locale.ROOT),
                        url = item.images.downsized_large.url
                    ))
                    context.startActivity(intent)
                }
                binding.executePendingBindings()
            }
        }
    }
}