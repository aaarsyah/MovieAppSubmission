package com.example.capstoneawalexpert.core.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneawalexpert.core.R
import com.example.capstoneawalexpert.core.databinding.ItemListSearchBinding
import com.example.capstoneawalexpert.core.domain.model.Movie

class SearchAdapter(private val context: Context) : ListAdapter<Movie, SearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemListSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class SearchViewHolder(private val binding: ItemListSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                tvTitle.text = movie.originalTitle
                tvRating.text = context.getString(R.string.rating_text, movie.voteAverage.toString())
                tvReleaseDate.text = context.getString(R.string.release_date_text, movie.releaseDate)

                Glide.with(root.context)
                    .load("https://image.tmdb.org/t/p/w500" + movie.posterPath)
                    .into(ivPoster)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(movie)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
