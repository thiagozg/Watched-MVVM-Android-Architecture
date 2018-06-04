package br.com.watched.details

import android.arch.lifecycle.Observer
import android.os.Bundle
import br.com.watched.core.base.BaseActivity
import br.com.watched.core.model.api.ApiResponse
import br.com.watched.core.model.domain.DetailsResponseVO
import br.com.watched.core.util.Constants.KEY_IMDB_ID
import br.com.watched.core.util.loadGlide
import br.com.watched.details.R.id.*
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

/**
 * Created by thiagozg on 18/11/2017.
 */
class DetailsActivity(override val layoutResId: Int = R.layout.activity_details) : BaseActivity() {

    @Inject lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.requestDetails(intent.getStringExtra(KEY_IMDB_ID))
        observeLoadingStatus(viewModel, clDetails, loadingIndicator)
        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel.getResponse().observe(this, Observer<ApiResponse<DetailsResponseVO>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        if (response.data is DetailsResponseVO) {
            val vo = response.data as DetailsResponseVO
            initLayout(vo)
        } else super.processResponse(response)
    }

    private fun initLayout(vo: DetailsResponseVO) {
        ivPoster.loadGlide(vo.poster, false)
        tvTitle.text = vo.title
        tvDescription.text = "${getString(R.string.type)}: ${vo.type} - ${getString(R.string.year)}: ${vo.year}"
        tvGenre.text = vo.genre
        tvActors.text = vo.actors
        tvLanguage.text = vo.language
        tvAwards.text = vo.awards
    }

}

