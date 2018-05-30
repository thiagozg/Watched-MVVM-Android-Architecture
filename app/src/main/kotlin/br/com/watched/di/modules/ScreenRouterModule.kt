package br.com.watched.di.modules

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.watched.WatchedApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScreenRouterModule {

    @Provides
    @Singleton
    fun provideScreenRouter() = ScreenRouterModule()

}