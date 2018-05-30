package br.com.watched.di.modules

import android.content.Context
import android.content.SharedPreferences
import br.com.watched.WatchedApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
class AndroidModule {

    private val PREFERENCES = "Preferences"

    @Provides
    @Singleton
    fun provideContext(application: WatchedApplication) = application.getApplicationContext()

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context) = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

}
