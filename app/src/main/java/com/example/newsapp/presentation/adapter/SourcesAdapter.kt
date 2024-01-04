package com.example.newsapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.model.SourcesItem
import com.example.newsapp.databinding.CategoryItemLayoutBinding

class SourcesAdapter() :
    RecyclerView.Adapter<SourcesAdapter.ViewHolder>() {
    private var listData = emptyList<SourcesItem>()

    private var selectedPosition = RecyclerView.NO_POSITION
    fun submitList(newsList: List<SourcesItem>) {
        listData = newsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])

    }


    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(private val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SourcesItem) {

        }
    }

}