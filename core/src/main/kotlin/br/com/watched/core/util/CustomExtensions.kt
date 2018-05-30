package br.com.watched.core.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import br.com.watched.core.model.domain.DetailsMovieReponseVO
import br.com.watched.core.model.domain.DetailsResponseVO
import br.com.watched.core.model.domain.DetailsSeriesResponseVO
import br.com.watched.core.util.Constants.TYPE_MOVIE
import br.com.watched.core.util.Constants.TYPE_SERIES
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.JsonObject

/**
 * Created by thiagozg on 12/11/2017.
 */

fun Activity.closeKeyboard() {
    val view = currentFocus
    if (view != null) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun ImageView.loadGlide(url: String? = null, isCircleCrop: Boolean = true) {
    val imageLoaded = Glide.with(context).load(url)
    if (isCircleCrop) imageLoaded.apply(RequestOptions.circleCropTransform())
    imageLoaded.into(this)
}

fun JsonObject.deserializeDetails(gson: Gson): DetailsResponseVO {
    val type = this.get("Type").asString

    return when {
        type.equals(TYPE_MOVIE) -> gson.fromJson(this, DetailsMovieReponseVO::class.java)

        type.equals(TYPE_SERIES) -> gson.fromJson(this, DetailsSeriesResponseVO::class.java)

        else -> DetailsResponseVO()
    }
}
