package com.test.blitz.domain.common

import com.test.blitz.core.Resource
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
suspend fun <T> safeResource(@BuilderInference block: suspend () -> T): Resource<T> = try {
    val resource = block()
    Resource.Success(resource)
} catch (e: Exception) {
    Resource.Error(e)
}