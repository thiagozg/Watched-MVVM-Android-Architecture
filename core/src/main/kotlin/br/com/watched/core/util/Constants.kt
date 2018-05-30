package br.com.watched.core.util

import br.com.watched.core.BuildConfig

/**
 * Created by thiagozg on 08/11/2017.
 */
object Constants {
    const val BASE_API_KEY = "?apikey=" + BuildConfig.API_KEY + "&"
    const val TYPE_MOVIE = "movie"
    const val TYPE_SERIES = "series"

    const val KEY_IMDB_ID = "key_imdbID"
}