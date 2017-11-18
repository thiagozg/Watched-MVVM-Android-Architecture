package br.com.watched.features.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.watched.model.interactor.OmdbUseCase
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.model.api.ApiResponse
import br.com.watched.util.SchedulersFacade
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by thiagozg on 11/11/2017.
 */

// extends from AndroidViewModel if you need the application context.
class SearchViewModel(private val useCase: OmdbUseCase) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val viewResponse = MutableLiveData<ApiResponse<SearchResponse>>()

    fun searchByQuery(query: String) {
        loadResultList(useCase.searchByQuery(query))
    }

    fun getResponse(): MutableLiveData<ApiResponse<SearchResponse>> {
        return viewResponse
    }

    private fun loadResultList(single: Single<SearchResponse>) {
        disposables.add(single
                .subscribeOn(SchedulersFacade.io())
                .observeOn(SchedulersFacade.ui())
                .doOnSubscribe { viewResponse.value = ApiResponse.loading(true) } // while is requesting
                .doAfterTerminate { viewResponse.value = ApiResponse.loading(false) } // after response is ready
                .subscribe( { apiResponse -> viewResponse.value = ApiResponse.success(apiResponse) },
                            { throwable -> viewResponse.value = ApiResponse.error(throwable) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}