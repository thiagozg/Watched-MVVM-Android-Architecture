package br.com.watched.di.modules

import br.com.watched.features.details.DetailsActivity
import br.com.watched.features.details.DetailsModule
import br.com.watched.features.search.HomeActivity
import br.com.watched.features.search.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(DetailsModule::class))
    internal abstract fun bindDetailsActivity(): DetailsActivity

    // Add bindings for other sub-components here
}
