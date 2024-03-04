package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.base.paging.PagingUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFoodsUseCase @Inject constructor(
    private val foodPersistance: FoodPersistance
) : PagingUseCase() {

    suspend operator fun invoke(
        filters: FoodFilters,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Flow<List<Food>> = pagingFlowOf(dispatcher) {
        foodPersistance.getFoods(it, filters)
    }
}
