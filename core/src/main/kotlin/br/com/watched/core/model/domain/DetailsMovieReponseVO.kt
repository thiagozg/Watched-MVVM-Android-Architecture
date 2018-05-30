package br.com.watched.core.model.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by thiagozg on 18/11/2017.
 */
data class DetailsMovieReponseVO(
        @SerializedName("DVD")
        val dvd: String? = "",
        @SerializedName("BoxOffice")
        val boxOffice: String? = "",
        @SerializedName("Production")
        val production: String? = "",
        @SerializedName("Website")
        val website: String? = ""
) : DetailsResponseVO()
