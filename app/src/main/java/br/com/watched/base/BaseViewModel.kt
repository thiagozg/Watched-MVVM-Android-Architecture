package br.com.watched.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by thiagozg on 18/11/2017.
 */
abstract class BaseViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}