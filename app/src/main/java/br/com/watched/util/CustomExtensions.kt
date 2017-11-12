package br.com.watched.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

fun ImageView.loadCircleGlide(url: String? = null) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}
