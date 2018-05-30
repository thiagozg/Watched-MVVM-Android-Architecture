package br.com.watched.di.modules

import br.com.watched.details.DetailsActivity
import br.com.watched.details.DetailsModule
import br.com.watched.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector // TODO: test empty module
    internal abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [DetailsModule::class])
    internal abstract fun bindDetailsActivity(): DetailsActivity

    // Add bindings for other sub-components here
}
