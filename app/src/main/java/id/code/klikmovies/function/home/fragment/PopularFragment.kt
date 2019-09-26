package id.code.klikmovies.function.home.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import id.code.klikmovies.App

import id.code.klikmovies.R
import id.code.klikmovies.databinding.FragmentPopularBinding
import id.code.klikmovies.model.MovieParser
import id.code.klikmovies.model.Movie
import id.code.klikmovies.function.home.adapter.MovieAdapter
import id.code.klikmovies.function.home.adapter.OnItemClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import id.code.klikmovies.util.PrefManager

class PopularFragment : Fragment(), OnItemClickListener  {


    lateinit var binding : FragmentPopularBinding
    lateinit var movieAdapter : MovieAdapter;


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(this)
        binding.recylerView.layoutManager = GridLayoutManager(context, 2)
        binding.recylerView.adapter = movieAdapter
        App().services.getAllPopularMovies().enqueue(object  : Callback<MovieParser<Movie>> {
            override fun onFailure(call: Call<MovieParser<Movie>>, t: Throwable) {
               Log.d("responses", t.message)
            }
            override fun onResponse(call: Call<MovieParser<Movie>>,
                                    response: Response<MovieParser<Movie>>) {
                val movies : List<Movie> = response.body()?.results ?: emptyList()
                movieAdapter.movies = movies
                movieAdapter.notifyDataSetChanged()
            }
        })

    }

    override fun onItemClick(view: View, position: Int, movie: Movie) {
        if (view.id == R.id.image_favorite){
            context?.let { PrefManager(it).saveMovieAsFavorite(movie) }

            val savedMovie = context?.let { PrefManager(it).getFavoriteMovie()}

            Toast.makeText(context, "As favorite", Toast.LENGTH_SHORT).show()
        } else {

            context?.let { PrefManager(it).removeFavoriteMovie(movie) }

            val savedMovie = context?.let { PrefManager(it).getFavoriteMovie()}
            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
        }
    }


}
