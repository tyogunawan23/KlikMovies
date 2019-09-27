package id.code.klikmovies.function.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import id.code.klikmovies.App
import id.code.klikmovies.R
import id.code.klikmovies.databinding.ActivityDetailBinding
import id.code.klikmovies.function.base.BaseActivity
import id.code.klikmovies.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var binding: ActivityDetailBinding
    var id: Int? = null


    companion object{
        private val ID = "id"

        fun newIntent (context: Context?, id :Int) : Intent {
            val intent = Intent (context, DetailActivity::class.java)
            intent.putExtra(ID, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.swipeOnRefresh.setOnRefreshListener(this)
        id = intent.getIntExtra(ID, 0)
        onRefresh()
    }

    override fun onRefresh() {
        if (id != null && id != 0) {
            getMovieDetail(id!!)
        }  else {
            binding.swipeOnRefresh.isRefreshing= false
        }
    }

    fun getMovieDetail(id: Int) {
        binding.swipeOnRefresh.isRefreshing= true
        App().services.getMovieDetail(id.toString()).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                binding.swipeOnRefresh.isRefreshing= false
            }
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                binding.swipeOnRefresh.isRefreshing= false
                val movie: Movie? = response.body()
                val posterUrl :String = App.Companion.posterPath + movie!!.posterPath
                Glide.with(this@DetailActivity)
                    .load(posterUrl)
                    .into(binding.imagePoster)
                binding.overview.text = movie.overview
                binding.textTitle.text = movie.title
                binding.textReleaseDate.text = movie.releaseDate

            }
        })
    }

}
