package id.code.klikmovies.function.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.code.klikmovies.App
import id.code.klikmovies.R
import id.code.klikmovies.model.Genre
import id.code.klikmovies.model.Movie
import id.code.klikmovies.util.PrefManager
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<ItemViewHolder> {

    lateinit var movies : List<Movie>
    var listener : OnItemClickListener? = null

    constructor(listener : OnItemClickListener){
        this.movies = emptyList()
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false), this.listener)
    }

    override fun getItemCount(): Int {
       return movies.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(movies[position])
    }

}

    class ItemViewHolder(itemView: View, onClick: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.text_title
        private val image : ImageView = itemView.image_cover
        private val genreText = itemView.text_genre
        private var listener : OnItemClickListener? = null

        init {
            this.listener = onClick
        }

        fun bind (movie : Movie){
            title.text = movie.title
            val posterUrl :String = App.Companion.posterPath + movie.posterPath
            val genre :String = getGenreAsString(movie.genreIds)
            genreText.text = genre
            Glide.with(itemView.context)
                .load(posterUrl)
                .into(image)

            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition, movie)
            }

        }

        fun  getGenreAsString (genreIds : List<Int>) : String{
            var genreMovie : String =""
            val genrePref : List<Genre>? = PrefManager(itemView.context).getGenreList()
            if (genrePref != null) {
                for (genre : Genre in genrePref){
                    for (ids :Int  in genreIds){
                        if (genre.id.equals(ids)){
                            val sb = StringBuilder()
                            if (genreMovie.isNullOrEmpty()){
                                genreMovie = sb.append(genreMovie).append(genre.name).toString()
                            } else {
                                genreMovie = sb.append(genreMovie).append(", ").append(genre.name).toString()
                            }

                        }
                    }

                }
            }
            return genreMovie;
        }

    }

public interface OnItemClickListener {
    fun onItemClick(position : Int, movie : Movie)
}
