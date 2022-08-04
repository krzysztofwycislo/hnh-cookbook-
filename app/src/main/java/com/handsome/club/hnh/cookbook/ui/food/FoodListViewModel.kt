package com.handsome.club.hnh.cookbook.ui.food

import androidx.lifecycle.viewModelScope
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.GetFoodsUseCase
import com.handsome.club.hnh.cookbook.ui.base.BaseViewModel
import com.handsome.club.hnh.cookbook.ui.base.ScreenError
import com.handsome.club.hnh.cookbook.ui.base.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FoodListScreenState(
    val initialLoading: Boolean = true,
    val error: ScreenError? = null,
    val foods: List<Food>? = null,
    val selectedFoodId: Long? = null
) : ScreenState

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val getFoodsUseCase: GetFoodsUseCase
) : BaseViewModel<FoodListScreenState>(FoodListScreenState()) {

    init {
        viewModelScope.launch {
            getFoodsUseCase().collect {
                screenState = screenState.copy(initialLoading = false, error = null, foods = it)
            }
        }
    }

    fun onFoodSelection(food: Food) {
        screenState = screenState.copy(
            selectedFoodId = food.id.takeIf { food.id != screenState.selectedFoodId }
        )
    }

}
