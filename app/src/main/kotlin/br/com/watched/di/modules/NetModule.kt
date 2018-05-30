package br.com.watched.di.modules

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.watched.WatchedApplication
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    val TIMEOUT_SECONDS = 60

    @Provides
    @Singleton
    fun provideSharedPreferences(app: WatchedApplication) = PreferenceManager.getDefaultSharedPreferences(app)

    @Provides
    @Singleton
    fun provideHttpCache(app: WatchedApplication): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
                .addNetworkInterceptor(logging)
                .cache(cache)
                .build()
    }

}
