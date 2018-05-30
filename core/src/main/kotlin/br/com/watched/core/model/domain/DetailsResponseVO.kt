package br.com.watched.core.model.domain

import com.google.gson.annotations.SerializedName

/**
 * Created by thiagozg on 20/11/2017.
 */
open class DetailsResponseVO(
        @SerializedName("Title")
        val title: String? = "",
        @SerializedName("Year")
        val year: String? = "",
        @SerializedName("Rated")
        val rated: String? = "",
        @SerializedName("Released")
        val released: String? = "",
        @SerializedName("Runtime")
        val runtime: String? = "",
        @SerializedName("Genre")
        val genre: String? = "",
        @SerializedName("Director")
        val director: String? = "",
        @SerializedName("Writer")
        val writer: String? = "",
        @SerializedName("Actors")
        val actors: String? = "",
        @SerializedName("Plot")
        val plot: String? = "",
        @SerializedName("Language")
        val language: String? = "",
        @SerializedName("Country")
        val country: String? = "",
        @SerializedName("Awards")
        val awards: String? = "",
        @SerializedName("Poster")
        val poster: String? = "",
        @SerializedName("Metascore")
        val metascore: String? = "",
        @SerializedName("imdbRating")
        val imdbRating: String? = "",
        @SerializedName("imdbVotes")
        val imdbVotes: String? = "",
        @SerializedName("imdbID")
        val imdbID: String? = "",
        @SerializedName("Type")
        val type: String? = "",
        @SerializedName("Response")
        val response: String? = "",
        @SerializedName("Ratings")
        val ratings: List<RatingsVO>? = ArrayList()
)