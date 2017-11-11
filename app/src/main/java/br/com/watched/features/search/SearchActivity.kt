package br.com.watched.features.search

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.watched.R
import br.com.watched.model.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var viewModel: SearchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        observeLoadingStatus()
        observeSearchResponse()
    }

    // todo: usar o OnClick do Kotlin

    // todo: terminar isso depois da view estiver pronta
    private fun observeLoadingStatus() {
//        viewModel?.getLoadingStatus()?.observe(this, { isLoading -> processLoadingStatus(isLoading) })
    }

    private fun observeSearchResponse() {
//        viewModel?.getResponse()?.observe(this, { response -> processResponse(response) })
    }

//    private fun processLoadingStatus(isLoading: Boolean) {
//        greetingTextView.setVisibility(if (isLoading) View.GONE else View.VISIBLE)
//        loadingIndicator.setVisibility(if (isLoading) View.VISIBLE else View.GONE)
//    }
//
//    private fun processResponse(response: Response<String>) {
//        when (response.status) {
//            SUCCESS -> greetingTextView.setText(response.data)
//
//            ERROR -> {
//                Timber.e(response.error)
//                Toast.makeText(this, R.string.greeting_error, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
