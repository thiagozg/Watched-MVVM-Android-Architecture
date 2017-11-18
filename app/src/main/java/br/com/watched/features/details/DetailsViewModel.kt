package br.com.watched.features.details

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.watched.base.BaseViewModel
import br.com.watched.model.api.ApiResponse
import br.com.watched.model.interactor.OmdbUseCase
import br.com.watched.model.pojo.DetailsVO
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.util.SchedulersFacade
import io.reactivex.Single

/**
 * Created by thiagozg on 18/11/2017.
 */
class DetailsViewModel(private val useCase: OmdbUseCase) : BaseViewModel() {

    private val viewResponse = MutableLiveData<ApiResponse<DetailsVO>>()

    fun requestDetails(imdbID: String) {
        loadResultDetails(useCase.requestDetailsByImdbID(imdbID))
    }

    fun getResponse(): MutableLiveData<ApiResponse<DetailsVO>> {
        return viewResponse
    }

    private fun loadResultDetails(single: Single<DetailsVO>) {
        disposables.add(single
                .subscribeOn(SchedulersFacade.io())
                .observeOn(SchedulersFacade.ui())
                .doOnSubscribe { viewResponse.value = ApiResponse.loading(true) } // while is requesting
                .doAfterTerminate { viewResponse.value = ApiResponse.loading(false) } // after response is ready
                .subscribe( { detailsResponse -> viewResponse.value = ApiResponse.success(detailsResponse) },
                            { throwable -> viewResponse.value = ApiResponse.error(throwable) })
        )
    }

}