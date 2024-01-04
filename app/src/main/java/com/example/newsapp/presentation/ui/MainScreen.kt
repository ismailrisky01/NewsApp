package com.example.newsapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticlesItem
import com.example.newsapp.databinding.FragmentMainScreenBinding
import com.example.newsapp.presentation.adapter.CategoryAdapter
import com.example.newsapp.presentation.adapter.NewsAdapter
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import com.example.newsapp.presentation.viewmodel.NewsViewModelFactory


class MainScreen : Fragment(),CategoryAdapter.OnCategoryClickListener {
    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels { NewsViewModelFactory() }
    private val categories = listOf(
        "Business", "Entertainment", "General", "Health", "Science", "Sports", "Technology"
    )
    lateinit var categoryAdapter:CategoryAdapter
    lateinit var newsAdapter:NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter()
        categoryAdapter = CategoryAdapter({ viewModel.getNewsByCategory(it)},this)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCategories(newText)
                return false
            }
        })

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        binding.categoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            categoryAdapter.submitList(categories)

        }
        viewModel.newsData.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)
        }
        viewModel.getTopHeadlines()

    }

    private fun filterCategories(query: String?) {
        query?.let {
            val filteredCategories = viewModel.newsData.value?.filter { category ->
                category.title!!.contains(query, ignoreCase = true)
            } ?: emptyList()

            newsAdapter.submitList(filteredCategories)
        } ?: run {
            newsAdapter.submitList(viewModel.newsData.value ?: emptyList())
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCategoryClick(category: String) {

    }
}