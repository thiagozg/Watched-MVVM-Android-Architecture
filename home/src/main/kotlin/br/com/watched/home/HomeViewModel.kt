package br.com.watched.home

import android.arch.lifecycle.MutableLiveData
import br.com.watched.core.base.BaseViewModel
import br.com.watched.core.model.interactor.OmdbUseCase
import br.com.watched.core.model.domain.SearchResponseVO
import br.com.watched.core.model.api.ApiResponse
import br.com.watched.core.util.RxSchedulers
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */

// extends from AndroidViewModel if you need the application context.
class HomeViewModel
@Inject constructor(useCase: OmdbUseCase) : BaseViewModel(useCase) {

    private val viewResponse = MutableLiveData<ApiResponse<SearchResponseVO>>()

    fun searchByQuery(query: String) {
        loadResultList(useCase.requestSearchByQuery(query))
    }

    fun getResponse(): MutableLiveData<ApiResponse<SearchResponseVO>> {
        return viewResponse
    }

    private fun loadResultList(single: Single<SearchResponseVO>) {
        disposables.add(single
                .subscribeOn(RxSchedulers.io())
                .observeOn(RxSchedulers.ui())
                .doOnSubscribe { loadingStatus.setValue(true) } // while is requesting
                .doAfterTerminate { loadingStatus.setValue(false) } // after response is ready
                .subscribe( { viewResponse.value = ApiResponse.success(it) },
                            { viewResponse.value = ApiResponse.error(it) })
        )
    }

}