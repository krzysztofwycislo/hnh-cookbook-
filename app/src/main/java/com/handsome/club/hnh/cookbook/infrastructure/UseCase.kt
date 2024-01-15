package com.handsome.club.hnh.cookbook.infrastructure

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

interface UseCase


suspend inline fun <T> UseCase.tryUseCase(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline block: suspend () -> T
): Result<T> {
    return try {
        withContext(dispatcher) {
            Result.success(block())
        }
    } catch (e: Exception) {
        Timber.e(e)
        Result.failure(e)
    }
}
