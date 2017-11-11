package br.com.watched.di.modules

import br.com.watched.features.search.SearchActivity
import br.com.watched.features.search.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(SearchModule::class))
    internal abstract fun bindSearcActivity(): SearchActivity

    // Add bindings for other sub-components here
}
