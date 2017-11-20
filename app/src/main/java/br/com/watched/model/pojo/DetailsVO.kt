package br.com.watched.model.pojo

import org.parceler.Parcel

/**
 * Created by thiagozg on 18/11/2017.
 */
data class DetailsVO(
        val movie: DetailsMovieReponse? = null,
        val series: DetailsSeriesResponse? = null
)