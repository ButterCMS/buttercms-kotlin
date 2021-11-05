package com.example.buttercms.error

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class RestCallError(
    val errorMessage: String?,
    val response: Response<Any>? = null,
    val kind: Kind,
    val exception: Throwable? = null,
    val errorBody: String? = null
) :
    Error(errorMessage, exception) {

    /**
     * Identifies the event kind which triggered a [RestCallError].
     */
    enum class Kind {
        /**
         * An [IOException] occurred while communicating to the server.
         */
        NETWORK,

        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    companion object {
        fun networkError(exception: IOException): RestCallError {
            return RestCallError(
                errorMessage = exception.message,
                kind = Kind.NETWORK,
                exception = exception
            )
        }

        fun httpError(response: Response<Any>): RestCallError {
            val message = response.code().toString() + " " + response.message()
            return RestCallError(
                errorMessage = message,
                response = response,
                errorBody = response.errorBody()?.string().toString(),
                kind = Kind.HTTP
            )
        }

        fun unexpectedError(exception: Throwable): RestCallError {
            return RestCallError(
                errorMessage = exception.message,
                kind = Kind.UNEXPECTED,
                exception = exception
            )
        }
    }
}

open class Error(message: String?, cause: Throwable?) : Exception(message, cause)

fun <T : Any> Call<T>.enqueueCall(callback: ResponseCallback<T>.() -> Unit) {

    val responseCallback = ResponseCallback<T>()
    callback.invoke(responseCallback)
    this.enqueue(responseCallback)
}

class ResponseCallback<T : Any> : Callback<T> {

    var onSuccess: ((T) -> Unit)? = null
    var onSuccessEmpty: (() -> Unit)? = null
    var onFailure: ((t: RestCallError) -> Unit)? = null

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                onSuccess?.invoke(body)
            } else {
                onSuccessEmpty?.invoke()
            }
        } else {
            onFailure?.invoke(RestCallError.httpError(response as Response<Any>))
        }
    }

    override fun onFailure(call: Call<T>, error: Throwable) {
        val e = when (error) {
            is IOException -> RestCallError.networkError(error)
            else -> RestCallError.unexpectedError(error)
        }
        onFailure?.invoke(e)
    }
}

interface Callback<T, E : RestCallError?> {
    fun success(t: T)
    fun failure(error: E) // add status to failure as well
}
