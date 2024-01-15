package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.data.database.food.FoodDao
import com.handsome.club.hnh.cookbook.infrastructure.UseCase
import com.handsome.club.hnh.cookbook.infrastructure.tryUseCase
import com.handsome.club.hnh.cookbook.utils.logExecutionTime
import timber.log.Timber
import javax.inject.Inject


class PopulateFoodRepositoryUseCase @Inject constructor(
    private val persistance: FoodDao,
    private val source: FoodsSource,
) : UseCase {

    suspend operator fun invoke(): Result<Unit> = tryUseCase {
        if (persistance.isEmpty()) {

            val foods = logExecutionTime {
                Timber.i("Fetching foods")
                source.fetchAllFoods()
            }

            logExecutionTime {
                Timber.i("Saving foods")
                persistance.insertFoods(foods)
            }

            Timber.i("Processed ${foods.size} foods")
        }
    }
}
