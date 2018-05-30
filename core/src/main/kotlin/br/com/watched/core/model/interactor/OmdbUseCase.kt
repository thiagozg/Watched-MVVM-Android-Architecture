package br.com.watched.core.model.interactor

import br.com.watched.core.base.BaseUseCase
import br.com.watched.core.model.domain.DetailsResponseVO
import br.com.watched.core.model.domain.SearchResponseVO
import br.com.watched.core.model.repository.OmdbRepository
import br.com.watched.core.util.deserializeDetails
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