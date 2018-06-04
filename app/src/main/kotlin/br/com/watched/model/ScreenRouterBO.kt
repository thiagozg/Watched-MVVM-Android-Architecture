package br.com.watched.model

import android.content.Context
import android.content.Intent
import br.com.watched.core.base.ScreenRouter
import br.com.watched.details.DetailsActivity

/**
 * Created by thiagozg on 30/05/2018.
 */
object ScreenRouterBO : ScreenRouter {

    override fun getIntent(context: Context, screen: ScreenRouter.ScreenBO): Intent? {
        val clazz: Class<*>? = when (screen) {
            ScreenRouter.ScreenBO.DetailView -> DetailsActivity::class.java
            ScreenRouter.ScreenBO.ListView -> null // not necessarily
        }
        return if (clazz == null) null
               else Intent(context, clazz)
    }

}

