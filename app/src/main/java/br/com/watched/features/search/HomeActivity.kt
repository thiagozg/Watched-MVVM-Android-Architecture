package br.com.watched.features.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.watched.R
import br.com.watched.base.BaseActivity
import br.com.watched.features.details.DetailsActivity
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.model.api.ApiResponse
import br.com.watched.model.api.Status.*
import br.com.watched.util.Constants.KEY_IMDB_ID
import br.com.watched.util.UIListeners
import br.com.watched.util.closeKeyboard
import kotlinx.android.synthetic.main.activity_consult.*
import org.parceler.Parcels

class HomeActivity : BaseActivity(), SearchView.OnQueryTextListener, UIListeners.OnClickListener {

    private var viewModel: HomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consult)
        viewModel = ViewModelProviders
                        .of(this, viewModelFactory)
                        .get(HomeViewModel::class.java)

        observeSearchResponse()
    }

    private fun observeSearchResponse() {
        viewModel?.getResponse()?.observe(this, Observer<ApiResponse<SearchResponse>> {
            response -> response?.let { processResponse(it) }
        })
    }

    override fun processResponse(response: ApiResponse<*>) {
        when (response.status) {
            SUCCESS -> {
                if (response.data is SearchResponse) {
                    val adapter = HomeAdapter(response.data, this, this)
                    rv_result_search_list.layoutManager = LinearLayoutManager(this)
                    rv_result_search_list.setHasFixedSize(true)
                    rv_result_search_list.adapter = adapter
                } else Log.e(localClassName, "Unknown success json response.")
            }

            ERROR -> {
                Log.e(localClassName, response.error?.message, response.error)
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
            }

            LOADING -> {
                Log.d(localClassName, "The request is loading...")
                processLoadingStatus(rv_result_search_list, loading_indicator, response.isLoading)
            }
        }
    }

    override fun onClick(resultVO: SearchResponse.ResultVO) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(KEY_IMDB_ID, resultVO.imdbID)
        startActivity(intent)
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
        viewModel?.searchByQuery(query)
        return true
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}
