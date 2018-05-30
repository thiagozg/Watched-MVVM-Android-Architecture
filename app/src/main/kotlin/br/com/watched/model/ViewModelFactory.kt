package br.com.watched.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.watched.core.features.details.DetailsViewModel
import br.com.watched.core.model.interactor.OmdbUseCase
import br.com.watched.home.HomeViewModel
import javax.inject.Inject

/**
 * Created by thiagozg on 11/11/2017.
 */
// TODO: remove that later
@Suppress("UNCHECKED_CAST")
class ViewModelFactory
@Inject constructor(private val useCase: OmdbUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(useCase) as T

            modelClass.isAssignableFrom(DetailsViewModel::class.java) ->
                DetailsViewModel(useCase) as T

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }

}