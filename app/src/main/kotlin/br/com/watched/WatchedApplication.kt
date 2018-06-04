package br.com.watched

import br.com.watched.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WatchedApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

}
