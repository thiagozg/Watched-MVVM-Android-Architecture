package br.com.watched.util

import br.com.watched.model.pojo.SearchResponse

/**
 * Created by thiagozg on 12/11/2017.
 */
interface UIListeners {

    interface OnClickListener {
        fun onClick(resultVO: SearchResponse.ResultVO)
    }

}