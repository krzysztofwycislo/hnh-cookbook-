package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.infrastructure.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ToggleFavoriteFoodUseCase @Inject constructor(
    private val persistance: FoodPersistance
) : UseCase {

    suspend operator fun invoke(foodId: String): Boolean {
        return withContext(Dispatchers.IO) {
            persistance.toggleFavoriteFood(foodId)
        }
    }

}