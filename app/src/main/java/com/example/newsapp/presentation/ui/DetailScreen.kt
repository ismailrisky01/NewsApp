package com.example.newsapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticlesItem
import com.example.newsapp.databinding.FragmentDetailScreenBinding

class DetailScreen : Fragment() {
    private var _binding: FragmentDetailScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val articlesItem: ArticlesItem? = bundle?.getSerializable("articlesItem") as? ArticlesItem
        setData(articlesItem!!)
    }

    private fun setData(articlesItem: ArticlesItem) {
        binding.newsTitleTextView.text = articlesItem.title
        binding.newsDescriptionTextView.text = articlesItem.description
        Glide.with(requireContext()).load(articlesItem.urlToImage).placeholder(R.drawable.placeholder).into(binding.newsImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}