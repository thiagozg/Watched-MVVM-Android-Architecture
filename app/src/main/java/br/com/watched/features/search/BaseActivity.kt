package br.com.watched.features.search

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.*
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.model.api.ApiResponse
import br.com.watched.model.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Created by thiagozg on 18/11/2017.
 */
abstract class BaseActivity : AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    protected fun processLoadingStatus(resultView: View, progressView: View, isLoading: Boolean) {
        resultView.visibility = if (isLoading) GONE else VISIBLE
        progressView.visibility = if (isLoading) VISIBLE else GONE
    }

    protected abstract fun processResponse(response: ApiResponse<SearchResponse>)

}