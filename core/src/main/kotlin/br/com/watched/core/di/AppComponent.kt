package br.com.watched.core.di

import br.com.watched.core.WatchedApplication
import br.com.watched.core.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AndroidModule::class,
        NetModule::class,
        RetrofitModule::class,
        ApiModule::class,
        BuildersModule::class)
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
