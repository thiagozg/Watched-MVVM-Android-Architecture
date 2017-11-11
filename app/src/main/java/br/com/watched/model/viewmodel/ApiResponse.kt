package br.com.watched.model.viewmodel

import br.com.watched.model.viewmodel.Status.*

/**
 * ApiResponse holder provided to the UI
 *
 * @param <T>
 */
class ApiResponse<T>
constructor(val status: Status, val data: T?, val error: Throwable?) {
    companion object {

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): ApiResponse<T> {
            return ApiResponse(ERROR, null, error)
        }
    }
}
