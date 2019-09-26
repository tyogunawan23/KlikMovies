package id.code.klikmovies.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Movie(

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int> = ArrayList(),

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Double? = null

) : BaseModel()