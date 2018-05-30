package br.com.watched.core.util

import br.com.watched.core.model.domain.SearchResponseVO

/**
 * Created by thiagozg on 12/11/2017.
 */
interface UIListeners {

    interface OnClickListener {
        fun onClick(resultVO: SearchResponseVO.ResultVO)
    }

}