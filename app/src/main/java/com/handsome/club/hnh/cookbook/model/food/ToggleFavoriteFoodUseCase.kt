package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.infrastructure.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ToggleFavoriteFoodUseCase @Inject constructor(
//    private val persistance: FavoriteFoodDao, TODO replace persistance
) : UseCase {

    suspend operator fun invoke(foodId: String): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            Result.success(
//                persistance.toggleFavoriteFood(foodId)
                false
            )
        }
    }

}