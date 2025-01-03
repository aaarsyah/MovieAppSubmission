package com.example.capstoneawalexpert.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.example.capstoneawalexpert.R
import com.example.capstoneawalexpert.core.domain.model.Movie
import com.example.capstoneawalexpert.databinding.ActivityDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val detailMovie = getParcelableExtra(intent, EXTRA_DATA, Movie::class.java)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            val imageUrl = "https://image.tmdb.org/t/p/w500${detailMovie.posterPath}"

            supportActionBar?.title = detailMovie.originalTitle
            binding.contentDetailMovie.tvDetailDescription.text = detailMovie.overview
            binding.contentDetailMovie.tvTitle.text = detailMovie.originalTitle
            binding.contentDetailMovie.tvReleaseDate.text = detailMovie.releaseDate
            binding.contentDetailMovie.tvRating.text = detailMovie.voteAverage.toString()

            binding.ivDetailImage.loadImage(imageUrl)
            binding.contentDetailMovie.ivPoster.loadImage(imageUrl)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
