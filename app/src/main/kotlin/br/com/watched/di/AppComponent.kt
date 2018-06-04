package br.com.watched.di

import br.com.watched.WatchedApplication
import br.com.watched.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    NetModule::class,
    RetrofitModule::class,
    ApiModule::class,
    ScreenRouterModule::class,
    BuildersModule::class]
)
interface AppComponent : AndroidInjector<WatchedApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WatchedApplication>()

}
