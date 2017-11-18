package br.com.watched.features.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.watched.R
import br.com.watched.model.pojo.SearchResponse
import br.com.watched.util.UIListeners
import br.com.watched.util.loadCircleGlide
import kotlinx.android.synthetic.main.item_view.view.*

/**
 * Created by thiagozg on 12/11/2017.
 */
class SearchAdapter (
    private val searchResponse: SearchResponse,
    private val context: Context,
    private val listener: UIListeners.OnClickListener)
        : RecyclerView.Adapter<SearchAdapter.ResultHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate( R.layout.item_view, parent, false)

            val holder = ResultHolder(itemView, context)
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                val resultSelected = searchResponse.resultList[position]
                listener.onClick(resultSelected)
            }

            return holder
        }

        override fun onBindViewHolder(holder: ResultHolder, position: Int) {
            val result = searchResponse.resultList[position]
            holder.bindData(result)
        }

        override fun getItemCount(): Int {
            return searchResponse.resultList.size
        }

        class ResultHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

            fun bindData(vo: SearchResponse.ResultVO) {
                itemView.iv_channel_mini.loadCircleGlide(vo.poster)
                itemView.tv_title.text = vo.title
                itemView.tv_description.text =
                        "${context.getString(R.string.type)}: ${vo.type} - " +
                        "${context.getString(R.string.type)}: ${vo.year}"
            }

        }
}