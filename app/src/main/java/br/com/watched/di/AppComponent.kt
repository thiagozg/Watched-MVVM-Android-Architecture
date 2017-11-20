package br.com.watched.di

import br.com.watched.CustomApplication
import br.com.watched.di.modules.*
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
        fun application(application: CustomApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: CustomApplication)

}
