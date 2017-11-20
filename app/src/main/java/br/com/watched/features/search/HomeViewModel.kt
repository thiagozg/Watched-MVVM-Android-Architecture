package br.com.watched.features.search

import android.arch.lifecycle.MutableLiveData
import br.com.watched.base.BaseViewModel
import br.com.watched.model.interactor.OmdbUseCase
import br.com.watched.model.domain.SearchResponseVO
import br.com.watched.model.api.ApiResponse
import br.com.watched.util.SchedulersFacade
import io.reactivex.Single

/**
 * Created by thiagozg on 11/11/2017.
 */

// extends from AndroidViewModel if you need the application context.
class HomeViewModel(private val useCase: OmdbUseCase) : BaseViewModel() {

    private val viewResponse = MutableLiveData<ApiResponse<SearchResponseVO>>()

    fun searchByQuery(query: String) {
        loadResultList(useCase.requestSearchByQuery(query))
    }

    fun getResponse(): MutableLiveData<ApiResponse<SearchResponseVO>> {
        return viewResponse
    }

    private fun loadResultList(single: Single<SearchResponseVO>) {
        disposables.add(single
                .subscribeOn(SchedulersFacade.io())
                .observeOn(SchedulersFacade.ui())
                .doOnSubscribe { loadingStatus.setValue(true) } // while is requesting
                .doAfterTerminate { loadingStatus.setValue(false) } // after response is ready
                .subscribe( { searchResponse -> viewResponse.value = ApiResponse.success(searchResponse) },
                            { throwable -> viewResponse.value = ApiResponse.error(throwable) })
        )
    }

}