package br.com.watched.di.modules

import android.content.Context
import android.content.SharedPreferences
import br.com.watched.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
class AppModule {

    private val PREFERENCES = "Preferences"

    @Provides
    fun provideContext(application: App): Context {
        return application.getApplicationContext()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    }

}
