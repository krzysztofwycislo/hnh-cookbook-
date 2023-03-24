package com.handsome.club.hnh.cookbook.base.paging

import com.handsome.club.hnh.cookbook.infrastructure.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

abstract class PagingUseCase : UseCase {

    private var paginationFlow = createPaginatedListFlow()


    protected fun <T> pagingFlowOf(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        loadPage: suspend (Pagination) -> T
    ): Flow<T> {
        return paginationFlow.map {
            withContext(dispatcher) {
                loadPage(it)
            }
        }
    }

    suspend fun loadNextPage() {
        paginationFlow.nextPage()
    }

    suspend fun refresh() {
        paginationFlow.refresh()
    }

}

fun createPaginatedListFlow() = PaginatedListFlow()

class PaginatedListFlow internal constructor(
    private val flow: MutableStateFlow<Pagination> = MutableStateFlow(Pagination()),
) : StateFlow<Pagination> by flow {

    private suspend fun value() = flow.first()

    suspend fun nextPage() = flow.emit(value().nextPage())

    suspend fun refresh() = flow.emit(value().refresh())

}