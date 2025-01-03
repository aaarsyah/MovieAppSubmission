package com.example.capstoneawalexpert.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneawalexpert.core.data.Resource
import com.example.capstoneawalexpert.core.ui.SearchAdapter
import com.example.capstoneawalexpert.detail.DetailMovieActivity
import com.example.capstoneawalexpert.search.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(searchModule)

        supportActionBar?.title = getString(R.string.search)

        searchAdapter = SearchAdapter(this)

        searchAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMovieActivity::class.java)
            intent.putExtra(DetailMovieActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchMovies(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

    }

    private fun searchMovies(query: String) {
        searchViewModel.searchMovies(query).observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.tvNoResults.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    result.data?.let {
                        if (it.isEmpty()) {
                            binding.tvNoResults.visibility = View.VISIBLE
                        } else {
                            binding.tvNoResults.visibility = View.GONE
                            searchAdapter.submitList(it)
                        }
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvNoResults.visibility = View.VISIBLE
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
