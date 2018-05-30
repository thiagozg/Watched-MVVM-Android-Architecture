package br.com.watched.core.base

import android.content.Context
import android.content.Intent

/**
 * Created by thiagozg on 30/05/2018.
 */
interface ScreenRouter {

    sealed class ScreenBO {
        object DetailView : ScreenBO()
        object ListView : ScreenBO()
    }

    fun getIntent(context: Context, screen: ScreenBO): Intent?
}