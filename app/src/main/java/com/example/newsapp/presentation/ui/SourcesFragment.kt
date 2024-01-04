package com.example.newsapp.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.model.ArticlesItem
import com.example.newsapp.data.model.Source
import com.example.newsapp.databinding.FragmentDetailScreenBinding
import com.example.newsapp.databinding.FragmentMainScreenBinding
import com.example.newsapp.databinding.FragmentSourcesBinding
import com.example.newsapp.presentation.adapter.NewsAdapter
import com.example.newsapp.presentation.viewmodel.NewsViewModel
import com.example.newsapp.presentation.viewmodel.NewsViewModelFactory


class SourcesFragment : Fragment() {
    private var _binding: FragmentSourcesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels { NewsViewModelFactory() }
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using ViewBinding
        _binding = FragmentSourcesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsAdapter = NewsAdapter()

        val bundle = arguments
        val source: Source = bundle?.getSerializable("sources") as Source

        binding.txtSource.text = source.name
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }
        viewModel.newsData.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)
        }
        viewModel.getNewsBySources(source.id!!)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}