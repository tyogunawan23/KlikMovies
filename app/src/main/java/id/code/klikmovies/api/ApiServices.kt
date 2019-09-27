package id.code.klikmovies.api

import id.code.klikmovies.model.GenreParser
import id.code.klikmovies.model.MovieParser
import id.code.klikmovies.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {

    @GET ("genre/movie/list")
    fun getAllGenre(): Call<GenreParser>

    @GET("movie/popular")
    fun getAllPopularMovies(): Call<MovieParser<Movie>>

    @GET("movie/top_rated")
    fun getAllTopRatedMovies() :Call<MovieParser<Movie>>

    @GET ("movie/{movie_id}")
    fun getMovieDetail(@Path(value = "movie_id") id :String ): Call <Movie>


}