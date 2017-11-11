package br.com.watched.model.repository

import br.com.watched.model.api.OmdbApi
import br.com.watched.model.pojo.SearchResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
class OmdbRepository
@Inject constructor() {

    @Inject
    lateinit var omdbApi: OmdbApi

    fun searchByQuery(query: String): Single<SearchResponse> {
        return omdbApi.searchByQuery(query)
    }

}