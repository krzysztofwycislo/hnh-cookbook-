package com.handsome.club.hnh.cookbook.model.food

import com.handsome.club.hnh.cookbook.data.database.FoodDao
import com.handsome.club.hnh.cookbook.data.database.FoodMapper
import com.handsome.club.hnh.cookbook.model.base.UseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetFoodsUseCase @Inject constructor(
    private val persistance: FoodDao,
    private val mapper: FoodMapper,
) : UseCase {

    suspend operator fun invoke() = persistance.getAllFoods().map(mapper::toModels)

}
