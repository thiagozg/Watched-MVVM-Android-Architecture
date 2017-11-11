package br.com.watched.model.interactor

import br.com.watched.model.pojo.SearchResponse
import br.com.watched.model.repository.OmdbRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
class OmdbUseCase
@Inject constructor(private val repository: OmdbRepository) {

    fun searchByQuery(query: String): Single<SearchResponse> {
        return repository.searchByQuery(query)
    }

}