package id.code.klikmovies.api

import id.code.klikmovies.model.GenreParser
import id.code.klikmovies.model.MovieParser
import id.code.klikmovies.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET ("genre/movie/list")
    fun getAllGenre(): Call<GenreParser>

    @GET("movie/popular")
    fun getAllPopularMovies(): Call<MovieParser<Movie>>


}