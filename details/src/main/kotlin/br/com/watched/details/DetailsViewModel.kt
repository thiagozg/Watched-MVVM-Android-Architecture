package br.com.watched.details

import android.arch.lifecycle.MutableLiveData
import br.com.watched.core.base.BaseViewModel
import br.com.watched.core.model.api.ApiResponse
import br.com.watched.core.model.domain.DetailsResponseVO
import br.com.watched.core.model.interactor.OmdbUseCase
import br.com.watched.core.util.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by thiagozg on 18/11/2017.
 */
class DetailsViewModel
@Inject constructor(useCase: OmdbUseCase) : BaseViewModel(useCase) {

    private val viewResponse = MutableLiveData<ApiResponse<DetailsResponseVO>>()

    fun requestDetails(imdbID: String) {
        loadResultDetails(useCase.requestDetailsByImdbID(imdbID))
    }

    fun getResponse(): MutableLiveData<ApiResponse<DetailsResponseVO>> {
        return viewResponse
    }

    private fun loadResultDetails(single: Single<DetailsResponseVO>) {
        disposables.add(single
                .subscribeOn(RxSchedulers.io())
                .observeOn(RxSchedulers.ui())
                .doOnSubscribe { loadingStatus.setValue(true) } // while is requesting
                .doAfterTerminate { loadingStatus.setValue(false) } // after response is ready
                .subscribe( { detailsResponse -> viewResponse.value = ApiResponse.success(detailsResponse) },
                            { throwable -> viewResponse.value = ApiResponse.error(throwable) })
        )
    }

}