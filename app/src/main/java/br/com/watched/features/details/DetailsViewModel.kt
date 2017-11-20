package br.com.watched.features.details

import android.arch.lifecycle.MutableLiveData
import br.com.watched.base.BaseViewModel
import br.com.watched.model.api.ApiResponse
import br.com.watched.model.interactor.OmdbUseCase
import br.com.watched.model.domain.DetailsResponseVO
import br.com.watched.util.SchedulersFacade
import io.reactivex.Single

/**
 * Created by thiagozg on 18/11/2017.
 */
class DetailsViewModel(private val useCase: OmdbUseCase) : BaseViewModel() {

    private val viewResponse = MutableLiveData<ApiResponse<DetailsResponseVO>>()

    fun requestDetails(imdbID: String) {
        loadResultDetails(useCase.requestDetailsByImdbID(imdbID))
    }

    fun getResponse(): MutableLiveData<ApiResponse<DetailsResponseVO>> {
        return viewResponse
    }

    private fun loadResultDetails(single: Single<DetailsResponseVO>) {
        disposables.add(single
                .subscribeOn(SchedulersFacade.io())
                .observeOn(SchedulersFacade.ui())
                .doOnSubscribe { loadingStatus.setValue(true) } // while is requesting
                .doAfterTerminate { loadingStatus.setValue(false) } // after response is ready
                .subscribe( { detailsResponse -> viewResponse.value = ApiResponse.success(detailsResponse) },
                            { throwable -> viewResponse.value = ApiResponse.error(throwable) })
        )
    }

}