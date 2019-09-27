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

class FavoriteFragment  : Fragment(), OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

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
        binding.swipeOnRefresh.setOnRefreshListener(this)
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
        } else {
            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRefresh() {
        binding.swipeOnRefresh.isRefreshing = true
        movieAdapter.movies = PrefManager(activity!!.applicationContext).getFavoriteMovie()!!
        movieAdapter.notifyDataSetChanged()
        binding.swipeOnRefresh.isRefreshing = false
    }


}
