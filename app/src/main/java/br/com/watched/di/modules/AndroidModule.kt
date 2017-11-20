package br.com.watched.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.watched.CustomApplication
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
    fun provideContext(application: CustomApplication): Context {
        return application.getApplicationContext()
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
    }

}
