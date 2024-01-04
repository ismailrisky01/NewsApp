package com.example.newsapp.presentation.adapter

// CategoryAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.databinding.CategoryItemLayoutBinding

class CategoryAdapter(val onChangeCategory:(category:String)->Unit, val onCategoryClickListener: OnCategoryClickListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var listData = emptyList<String>()

    private var selectedPosition = RecyclerView.NO_POSITION
    fun submitList(newsList: List<String>) {
        listData = newsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listData[position],onChangeCategory,position == selectedPosition)

    }


    override fun getItemCount(): Int = listData.size

    inner class CategoryViewHolder(private val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String,onChangeCategory:(category:String)->Unit, isSelected: Boolean) {
            if (isSelected) {
                itemView.setBackgroundResource(R.drawable.selected_category_background)
            } else {
                itemView.setBackgroundResource(R.drawable.unselected_category_background)
            }
            binding.categoryNameTextView.text = data
            itemView.setOnClickListener {
                val previousSelectedPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedPosition)

                // Notify the listener about the click
                onCategoryClickListener.onCategoryClick(data)
                onChangeCategory.invoke(data) }
        }
    }
    interface OnCategoryClickListener {
        fun onCategoryClick(category: String)
    }
}