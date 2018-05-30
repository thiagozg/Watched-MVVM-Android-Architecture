package br.com.watched.di

import br.com.watched.WatchedApplication
import br.com.watched.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    NetModule::class,
    RetrofitModule::class,
    ApiModule::class,
    BuildersModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WatchedApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: WatchedApplication)

}
