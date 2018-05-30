package br.com.watched.core.model.repository

import br.com.watched.core.model.api.OmdbApi
import br.com.watched.core.model.domain.SearchResponseVO
import com.google.gson.JsonObject
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
class OmdbRepository
@Inject constructor() {

    @Inject lateinit var omdbApi: OmdbApi

    fun searchByQuery(query: String): Single<SearchResponseVO> {
        return omdbApi.searchByQuery(query)
    }

    fun getDetailsByImdbID(imdbId: String): Single<JsonObject> {
        return omdbApi.getDetailsByImdbID(imdbId)
    }

}