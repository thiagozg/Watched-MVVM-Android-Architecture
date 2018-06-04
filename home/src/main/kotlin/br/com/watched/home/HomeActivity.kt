package br.com.watched.home

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.watched.core.base.BaseActivity
import br.com.watched.core.base.ScreenRouter
import br.com.watched.core.model.api.ApiResponse
import br.com.watched.core.model.api.Status.*
import br.com.watched.core.model.domain.SearchResponseVO
import br.com.watched.core.util.Constants.KEY_IMDB_ID
import br.com.watched.core.util.UIListeners
import br.com.watched.core.util.closeKeyboard
import br.com.watched.home.R.id.loadingIndicator
import br.com.watched.home.R.id.rvResultSearch
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity(override val layoutResId: Int = R.layout.activity_home) :
        BaseActivity(), SearchView.OnQueryTextListener, UIListeners.OnClickListener {

    @Inject lateinit var viewModel: HomeViewModel
    @Inject lateinit var screenRouter: ScreenRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLoadingStatus(viewModel, rvResultSearch, loadingIndicator)
        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel.getResponse().observe(this, Observer<ApiResponse<SearchResponseVO>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        when (response.status) {
            SUCCESS -> {
                if (response.data is SearchResponseVO) {
                    val adapter = HomeAdapter(response.data as SearchResponseVO, this, this)
                    rvResultSearch.layoutManager = LinearLayoutManager(this)
                    rvResultSearch.setHasFixedSize(true)
                    rvResultSearch.adapter = adapter
                } else super.processResponse(response)
            }

            ERROR -> {
                Log.e(localClassName, response.error?.message, response.error)
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(resultVO: SearchResponseVO.ResultVO) {
        val intent: Intent? = screenRouter.getIntent(this, ScreenRouter.ScreenBO.DetailView)
        intent?.let {
            intent.apply { putExtra(KEY_IMDB_ID, resultVO.imdbID) }
                  .run { startActivity(this) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                this@HomeActivity.closeKeyboard()
                processResponse(ApiResponse(LOADING, data = null, isLoading = false))
                return true
            }
        })

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        closeKeyboard()
        viewModel.searchByQuery(query)
        return true
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}
