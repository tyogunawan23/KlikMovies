package id.code.klikmovies.function.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.code.klikmovies.R
import id.code.klikmovies.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<ItemViewHolder> {

    private var movies : List<Movie>

    constructor(movies : List<Movie>){
        this.movies = movies
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
        private val image = itemView.image_cover

        fun bind (movie : Movie){
            title.text = movie.title
           // image.
        }

    }
