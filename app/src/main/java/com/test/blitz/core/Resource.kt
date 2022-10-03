package com.test.blitz.core

/**
 * Resource used containing either a [Success] with a set data or an [Error] with a set [Exception]
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val exception: Exception) : Resource<T>()
}

/**
 * Creates a resource that calls the correct callbacks with automatically cast resource values
 * See [ResourceScope] for optional callbacks
 *
 * ```kotlin
 *  fun getModules() {
 *      resource({ getPhotos() }) {
 *          onSuccess { photos ->
 *              // do *something* with the modules
 *          }
 *          onError { error ->
 *              // manage error here
 *          }
 *          finally {
 *              // clean-up, finish loading...
 *          }
 *      }
 *  }
 * ```
 *
 * As per now, the resource request is triggered as soon as it is built, if we need to postpone it
 * we'll update this class to support this feature
 */
suspend fun <T>resource(request: suspend () -> Resource<T>, block: ResourceScope<T>.() -> Unit) {
    val builder = ResourceScopeImpl(request).apply(block)
    builder.request()
}

/**
 * Receiver scope used by the children of [resource]
 *
 */
interface ResourceScope<T> {
    fun onSuccess(listener: suspend (T) -> Unit)
    fun onError(listener: suspend (Exception) -> Unit)
    fun onComplete(listener: suspend () -> Unit)
}

private class ResourceScopeImpl<T>(val run: suspend () -> Resource<T>): ResourceScope<T> {
    private var onSuccessListener: (suspend (T) -> Unit)? = null
    private var onErrorListener: (suspend (Exception) -> Unit)? = null
    private var onCompleteListener: (suspend () -> Unit)? = null

    override fun onSuccess(listener: suspend (T) -> Unit) {
        onSuccessListener = listener
    }
    override fun onError(listener: suspend (Exception) -> Unit) {
        onErrorListener = listener
    }

    override fun onComplete(listener: suspend () -> Unit) {
        onCompleteListener = listener
    }

    suspend fun request() {
        when (val res = run()) {
            is Resource.Error -> { onErrorListener?.invoke(res.exception) }
            is Resource.Success -> { onSuccessListener?.invoke(res.data) }
        }
        onCompleteListener?.invoke()
    }
}