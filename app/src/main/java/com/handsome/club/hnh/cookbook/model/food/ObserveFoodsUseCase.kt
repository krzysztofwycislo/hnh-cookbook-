package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.base.paging.PagingUseCase
import com.handsome.club.hnh.cookbook.data.database.food.FoodDao
import com.handsome.club.hnh.cookbook.data.database.food.FoodMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ObserveFoodsUseCase @Inject constructor(
    private val persistance: FoodDao,
    private val mapper: FoodMapper,
) : PagingUseCase() {

    suspend operator fun invoke(
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) = pagingFlowOf(dispatcher) {
        persistance.getFoods(it).let(mapper::toModels)
    }
}
