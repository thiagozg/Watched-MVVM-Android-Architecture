package br.com.watched.core.base

import com.google.gson.Gson
import javax.inject.Inject

/**
 * Created by thiagozg on 18/11/2017.
 */
abstract class BaseUseCase {

    @Inject protected lateinit var gson: Gson

}
