package br.com.watched.features.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import br.com.watched.R
import br.com.watched.base.BaseActivity
import br.com.watched.model.api.ApiResponse
import br.com.watched.model.pojo.DetailsVO
import br.com.watched.model.pojo.SearchResponse
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
        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel?.getResponse()?.observe(this, Observer<ApiResponse<DetailsVO>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        // TODO: melhorar esse codigo passando um objeto unico (o abstrato)
        if (response.data is DetailsVO) {
            val vo = response.data
            vo.movie.let {
                initLayout(vo.movie?.poster, vo.movie?.title, vo.movie?.type, vo.movie?.year,
                    vo.movie?.genre, vo.movie?.actors, vo.movie?.language, vo.movie?.awards)
            }

            vo.series.let {
                initLayout(vo.series?.poster, vo.series?.title, vo.series?.type, vo.series?.year,
                        vo.series?.genre, vo.series?.actors, vo.series?.language, vo.series?.awards)
            }
        } else Log.e(localClassName, "Unknown success json response.")
    }

    private fun initLayout(poster: String?, title: String?, type: String?, year: String?,
                           genre: String?, actors: String?, language: String?, awards: String?) {
        iv_poster.loadGlide(poster, false)
        tv_title.text = title
        tv_description.text = "${getString(R.string.type)}: ${type} - " +
                "${getString(R.string.year)}: ${year}"
        tv_genre.text = genre
        tv_actors.text = actors
        tv_language.text = language
        tv_awards.text = awards

        // todo : colocar mais campos aqui
    }

}

