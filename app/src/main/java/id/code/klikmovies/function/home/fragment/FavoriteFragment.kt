package id.code.klikmovies.function.home.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import id.code.klikmovies.R
import id.code.klikmovies.databinding.FragmentFavoriteBinding
import id.code.klikmovies.function.home.adapter.MovieAdapter
import id.code.klikmovies.function.home.adapter.OnItemClickListener
import id.code.klikmovies.model.Movie
import id.code.klikmovies.util.PrefManager
import id.code.klikmovies.App

class FavoriteFragment  : Fragment(), OnItemClickListener{

    lateinit var binding: FragmentFavoriteBinding
    lateinit var movieAdapter: MovieAdapter;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter(this)
        binding.recylerView.layoutManager = GridLayoutManager(context, 2)
        binding.recylerView.adapter = movieAdapter
    }

    override fun onResume() {
        super.onResume()
        onRefresh()
    }

    override fun onItemClick(view: View, position: Int, movie: Movie) {
        if (view.id == R.id.image_favorite) {
            if (PrefManager(activity!!.applicationContext).isFavoriteMovie(movie)) {
                PrefManager(activity!!.applicationContext).removeFavoriteMovie(movie)
            } else {
                PrefManager(activity!!.applicationContext).saveMovieAsFavorite(movie)
            }
            movieAdapter.movies = PrefManager(activity!!.applicationContext).getFavoriteMovie()!!
            movieAdapter.notifyDataSetChanged()
            App().sendBroadcastFavoriteChanged(activity!!)
        } else {
            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
        }
    }

    fun onRefresh() {
        movieAdapter.movies = PrefManager(activity!!.applicationContext).getFavoriteMovie()!!
        movieAdapter.notifyDataSetChanged()
    }


}
