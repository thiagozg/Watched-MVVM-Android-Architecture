package br.com.watched.core.model.domain

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by thiagozg on 11/11/2017.
 */

data class SearchResponseVO(
            @SerializedName("totalResults")
            val totalResults: String = "0",
            @SerializedName("Response")
            val response: String = "",
            @SerializedName("Search")
            val resultList: List<ResultVO> = ArrayList()) {

    data class ResultVO(
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
