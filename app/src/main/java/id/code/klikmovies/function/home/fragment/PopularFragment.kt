package id.code.klikmovies.function.home.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import id.code.klikmovies.App

import id.code.klikmovies.R
import id.code.klikmovies.databinding.FragmentPopularBinding
import id.code.klikmovies.model.MapiParser
import id.code.klikmovies.model.Movie
import id.code.klikmovies.function.home.adapter.MovieAdapter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularFragment : Fragment() {

    lateinit var binding : FragmentPopularBinding
    var movieAdapter = MovieAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recylerView.layoutManager = GridLayoutManager(context, 2)
        binding.recylerView.adapter = movieAdapter
        App().services.getAllPopularMovies().enqueue(object  : Callback<MapiParser<Movie>> {
            override fun onFailure(call: Call<MapiParser<Movie>>, t: Throwable) {
               Log.d("responses", t.message)
            }
            override fun onResponse(call: Call<MapiParser<Movie>>,
                response: Response<MapiParser<Movie>>) {
                val movies : List<Movie> = response.body()?.results ?: emptyList()
                movieAdapter.movies = movies
                movieAdapter.notifyDataSetChanged()
            }
        })

    }


}
