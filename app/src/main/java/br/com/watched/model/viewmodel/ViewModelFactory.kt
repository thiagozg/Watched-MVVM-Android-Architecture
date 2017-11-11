package br.com.watched.model.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.watched.features.search.SearchViewModel
import br.com.watched.model.interactor.OmdbUseCase
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
class ViewModelFactory
@Inject constructor(private val useCase: OmdbUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}