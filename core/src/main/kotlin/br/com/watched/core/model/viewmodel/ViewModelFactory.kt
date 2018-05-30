package br.com.watched.core.model.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.watched.core.features.details.DetailsViewModel
import br.com.watched.core.features.search.HomeViewModel
import br.com.watched.core.model.interactor.OmdbUseCase
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory
@Inject constructor(private val useCase: OmdbUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(useCase) as T

            modelClass.isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(useCase) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}