package br.com.watched.model.interactor

import br.com.watched.base.BaseUseCase
import br.com.watched.model.domain.DetailsResponseVO
import br.com.watched.model.domain.SearchResponseVO
import br.com.watched.model.repository.OmdbRepository
import br.com.watched.util.deserializeDetails
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
class OmdbUseCase
@Inject constructor(private val repository: OmdbRepository) : BaseUseCase() {

    fun requestSearchByQuery(query: String): Single<SearchResponseVO> {
        return repository.searchByQuery(query)
    }

    fun requestDetailsByImdbID(imdbID: String): Single<DetailsResponseVO> {
        return repository.getDetailsByImdbID(imdbID)
                          .map { it.deserializeDetails(gson) }
    }

}