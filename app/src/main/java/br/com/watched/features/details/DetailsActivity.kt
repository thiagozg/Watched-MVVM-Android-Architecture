package br.com.watched.features.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import br.com.watched.R
import br.com.watched.base.BaseActivity
import br.com.watched.model.api.ApiResponse
import br.com.watched.model.domain.DetailsResponseVO
import br.com.watched.util.Constants.KEY_IMDB_ID
import br.com.watched.util.loadGlide
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by thiagozg on 18/11/2017.
 */
class DetailsActivity : BaseActivity() {

    private var viewModel: DetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        viewModel = ViewModelProviders
                        .of(this, viewModelFactory)
                        .get(DetailsViewModel::class.java)

        viewModel?.requestDetails(intent.getStringExtra(KEY_IMDB_ID))
        observeLoadingStatus(viewModel, cl_details, loading_indicator)
        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel?.getResponse()?.observe(this, Observer<ApiResponse<DetailsResponseVO>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        if (response.data is DetailsResponseVO) {
            val vo = response.data
            vo.let { initLayout(it) }
        } else super.processResponse(response)
    }

    private fun initLayout(vo: DetailsResponseVO) {
        iv_poster.loadGlide(vo.poster, false)
        tv_title.text = vo.title
        tv_description.text = "${getString(R.string.type)}: ${vo.type} - " +
                "${getString(R.string.year)}: ${vo.year}"
        tv_genre.text = vo.genre
        tv_actors.text = vo.actors
        tv_language.text = vo.language
        tv_awards.text = vo.awards
    }

}

