package br.com.watched.model.pojo

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

/**
 * Created by thiagozg on 18/11/2017.
 */
@Parcel
data class RatingsVO(
        @SerializedName("Source")
        val source: String? = "",
        @SerializedName("Value")
        val value: String? = ""
)