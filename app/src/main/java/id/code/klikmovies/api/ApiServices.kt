package id.code.klikmovies.api

import id.code.klikmovies.App
import id.code.klikmovies.model.Movie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("popular?api_key=ed96c7b8a694e614aeaa96f3b293a611")
    fun getAllPopularMovies(): Call<ResponseBody>

}