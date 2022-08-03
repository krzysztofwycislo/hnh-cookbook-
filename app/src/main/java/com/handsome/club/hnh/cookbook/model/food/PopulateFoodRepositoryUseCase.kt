package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.data.food.FoodDao
import com.handsome.club.hnh.cookbook.data.food.FoodMapper
import com.handsome.club.hnh.cookbook.model.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PopulateFoodRepositoryUseCase @Inject constructor(
    private val persistance: FoodDao,
    private val source: FoodsSource,
    private val mapper: FoodMapper
) : UseCase {

    suspend operator fun invoke(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            if (persistance.isEmpty()) {
                source.fetchAllFoods()
                    .map(mapper::toEntity)
                    .forEach { persistance.insertFood(it) }
            }

            Result.success(Unit)
        }
    }

}
