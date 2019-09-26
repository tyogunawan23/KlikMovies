package id.code.klikmovies.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import id.code.klikmovies.model.Genre
import id.code.klikmovies.model.Movie

/**
 * Created by Tyo CODEID on 12/1/2016.
 */

class PrefManager(private val _context: Context) {
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor


    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    fun getGenreList(): List<Genre>? {
        val data = pref.getString(GENRE, "")
        val gson = GsonBuilder().create()
        val genres : List<Genre> = gson.fromJson(data, Array<Genre>::class.java).toList()
        return genres
    }

    fun setGenreList(genre: List<Genre>?) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonGenre: String = gson.toJson(genre)
        editor.putString(GENRE, jsonGenre)
        editor.commit()
    }

    fun saveMovieAsFavorite(movie: Movie) {
        var moviesSaved: MutableList<Movie>
        val data = pref.getString(FAVORITE, "")
        val gson = GsonBuilder().create()
        moviesSaved = gson.fromJson(data,Array<Movie>::class.java).toMutableList()
        moviesSaved.add(movie)
        saveMovieFavoriteToJson(moviesSaved)
    }

    fun getFavoriteMovie () : List<Movie>?{
        val data = pref.getString(FAVORITE, "")
        val gson = GsonBuilder().create()
        val movies : List<Movie> = gson.fromJson(data, Array<Movie>::class.java).toList()
        return movies
    }

    fun saveMovieFavoriteToJson(movie: MutableList<Movie>?) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonMovie: String = gson.toJson(movie)
        editor.putString(FAVORITE, jsonMovie)
        editor.commit()
    }


    companion object {
        private val PRIVATE_MODE = 0
        private val PREF_NAME = "Preferences"
        private val GENRE = "genre_list"
        private val FAVORITE = "favorite"
    }

}