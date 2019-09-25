package id.code.klikmovies.model

import com.google.gson.annotations.SerializedName

data class Movie (

    val title: String,

    val year: Int,

    @SerializedName("poster_path")
    val posterPath: String

) : BaseModel()