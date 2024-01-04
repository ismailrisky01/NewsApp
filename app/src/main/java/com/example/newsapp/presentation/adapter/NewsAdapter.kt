package com.example.newsapp.presentation.adapter

// NewsAdapter.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticlesItem
import com.example.newsapp.databinding.NewsItemLayoutBinding

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var listData = emptyList<ArticlesItem>()


    fun submitList(newsList: List<ArticlesItem>) {
        listData = newsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class NewsViewHolder(private val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: ArticlesItem) {
            binding.textViewTitle.text = newsItem.title
            binding.textViewDescription.text = newsItem.description
            binding.textViewSources.text = "Source: ${newsItem.source?.name}"
            Glide.with(itemView.context).load(newsItem.urlToImage).apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(16)
                )
            ).into(binding.imageViewNews)

            val bundle = Bundle().apply {
                putSerializable("articlesItem", newsItem)
            }
            binding.container.setOnClickListener {
                it.findNavController().navigate(R.id.action_mainScreen_to_detailScreen, bundle)
            }
            val bundle1 = Bundle().apply {
                putSerializable("sources", newsItem.source)
            }

            binding.textViewSources.setOnClickListener {
                if (!newsItem.source?.id.isNullOrEmpty()) {
                    it.findNavController()
                        .navigate(R.id.action_mainScreen_to_sourcesFragment, bundle1)
                }


            }

        }
    }
}
