package br.com.watched.features.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.watched.R
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.model.viewmodel.ApiResponse
import br.com.watched.model.viewmodel.Status.ERROR
import br.com.watched.model.viewmodel.Status.SUCCESS
import br.com.watched.model.viewmodel.ViewModelFactory
import br.com.watched.util.UIListeners
import br.com.watched.util.closeKeyboard
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_consult.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, UIListeners.OnClickListener {

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

    private fun observeLoadingStatus() {
        viewModel?.getLoadingStatus()?.observe(this, Observer<Boolean> {
            isLoading -> isLoading?.let { processLoadingStatus(it) }
        })
    }

    private fun observeSearchResponse() {
        viewModel?.getResponse()?.observe(this, Observer<ApiResponse<SearchResponse>> {
            response -> response?.let { processResponse(it) }
        })
    }

    private fun processLoadingStatus(isLoading: Boolean) {
        rv_result_search_list.visibility = if (isLoading) View.GONE else View.VISIBLE
        loading_indicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun processResponse(response: ApiResponse<SearchResponse>) {
        when (response.status) {
            SUCCESS -> {
                rv_result_search_list.layoutManager = LinearLayoutManager(this)
                rv_result_search_list.setHasFixedSize(true)

                val adapter = SearchAdapter(response.data!!, this, this)
                rv_result_search_list.adapter = adapter
            }

            ERROR -> {
                Log.e(localClassName, response.error?.message, response.error)
                Toast.makeText(this, R.string.search_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(resultVO: SearchResponse.ResultVO) {
        TODO("levar para a tela de detalhes")
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
                this@SearchActivity.closeKeyboard()
                return true
            }
        })

        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel?.searchByQuery(query)
        return true
    }

    override fun onQueryTextChange(s: String): Boolean {
        return false
    }
}
