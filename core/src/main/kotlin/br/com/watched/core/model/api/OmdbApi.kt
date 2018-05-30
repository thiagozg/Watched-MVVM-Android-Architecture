package br.com.watched.core.model.api

import br.com.watched.core.BuildConfig
import br.com.watched.core.model.domain.SearchResponseVO
import br.com.watched.core.util.Constants.BASE_API_KEY
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by thiagozg on 08/11/2017.
 */
interface OmdbApi {

    @GET(BuildConfig.ENDPOINT + BASE_API_KEY)
    fun searchByQuery(@Query("s") query: String): Single<SearchResponseVO>

    @GET(BuildConfig.ENDPOINT + BASE_API_KEY)
    fun getDetailsByImdbID(@Query("i") imdbId: String): Single<JsonObject>

}