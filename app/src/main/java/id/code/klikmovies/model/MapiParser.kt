package id.code.klikmovies.model
import com.google.gson.annotations.SerializedName

class MapiParser<T : BaseModel> {

    @SerializedName("page")
    var page : Int? = 0

    @SerializedName("total_results")
    var totalResult : Int? = 0

    @SerializedName("total_pages")
    var totalPages : Int? = 0


    @SerializedName("results")
    var results: List<T>? = null


}