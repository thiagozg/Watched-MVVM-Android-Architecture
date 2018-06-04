package br.com.watched.core.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.watched.core.model.interactor.OmdbUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by thiagozg on 18/11/2017.
 */
open class BaseViewModel(protected val useCase: OmdbUseCase) : ViewModel() {

    protected val disposables = CompositeDisposable()

    val loadingStatus = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}