package br.com.watched.di.modules

import br.com.watched.core.features.details.DetailsActivity
import br.com.watched.core.features.details.DetailsModule
import br.com.watched.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector() // FIXME: test if its really necessery to use a EmptyModule here
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(DetailsModule::class))
    internal abstract fun bindDetailsActivity(): DetailsActivity

    // Add bindings for other sub-components here
}
