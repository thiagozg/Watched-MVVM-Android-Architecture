package br.com.watched.model.pojo

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by thiagozg on 18/11/2017.
 */
data class DetailsMovieReponse(
        @SerializedName("DVD")
        val dvd: String? = "",
        @SerializedName("BoxOffice")
        val boxOffice: String? = "",
        @SerializedName("Production")
        val production: String? = "",
        @SerializedName("Website")
        val website: String? = ""
) : AbstractDetailsResponse()
