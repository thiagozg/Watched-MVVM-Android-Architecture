package br.com.watched.model.pojo

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * Created by thiagozg on 11/11/2017.
 */

class SearchResponse (@SerializedName("totalResults")
                      val totalResults: String = "0",
                      @SerializedName("Response")
                      val response: String = "",
                      @SerializedName("Search")
                      val resultList: List<ResultVO> = ArrayList()) {

    class ResultVO(
            @SerializedName("Title")
            val title: String = "",
            @SerializedName("Year")
            val year: String = "",
            @SerializedName("imdbID")
            val imdbID: String = "",
            @SerializedName("Type")
            val type: String = "",
            @SerializedName("Poster")
            val poster: String = ""
    )
}
