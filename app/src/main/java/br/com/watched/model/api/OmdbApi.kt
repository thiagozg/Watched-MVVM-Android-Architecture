package br.com.watched.model.api

import br.com.watched.BuildConfig
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.util.Constants.BASE_API_KEY
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by thiagozg on 08/11/2017.
 */
interface OmdbApi {

    @GET(BuildConfig.ENDPOINT + BASE_API_KEY)
    fun searchByQuery(@Query("s") query: String): Single<SearchResponse>

}