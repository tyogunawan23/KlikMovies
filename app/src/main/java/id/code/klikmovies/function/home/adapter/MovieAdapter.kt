package id.code.klikmovies.function.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.code.klikmovies.App
import id.code.klikmovies.R
import id.code.klikmovies.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<ItemViewHolder> {

    lateinit var movies : List<Movie>

    constructor(){
        this.movies = emptyList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
       return movies.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

}

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.text_title
        private val image : ImageView = itemView.image_cover

        fun bind (movie : Movie){
            title.text = movie.title
            val posterUrl :String = App.Companion.posterPath + movie.posterPath
            Glide.with(itemView.context)
                .load(posterUrl)
                .into(image)


        }

    }
