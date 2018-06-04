package br.com.watched.di.modules

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.watched.WatchedApplication
import br.com.watched.core.base.ScreenRouter
import br.com.watched.model.ScreenRouterBO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScreenRouterModule {

    @Provides
    @Singleton
    fun provideScreenRouter(): ScreenRouter = ScreenRouterBO

}