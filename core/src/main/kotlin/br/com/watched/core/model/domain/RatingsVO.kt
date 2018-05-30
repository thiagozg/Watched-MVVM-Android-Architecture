package br.com.watched.core.model.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by thiagozg on 18/11/2017.
 */
data class RatingsVO(
        @SerializedName("Source")
        val source: String? = "",
        @SerializedName("Value")
        val value: String? = ""
)