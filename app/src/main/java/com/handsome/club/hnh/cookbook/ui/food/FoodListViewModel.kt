package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.ObserveFoodsUseCase
import com.handsome.club.hnh.cookbook.ui.base.BaseViewModel
import com.handsome.club.hnh.cookbook.ui.base.ScreenError
import com.handsome.club.hnh.cookbook.ui.base.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FoodListScreenState(
    val error: ScreenError? = null,
    val foods: List<Food>? = null,
    val selectedFoodId: Long? = null,
) : ScreenState

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val observeFoodsUseCase: ObserveFoodsUseCase,
) : BaseViewModel() {

    var screenState by mutableStateOf(FoodListScreenState())
        private set


    init {
        observe()
    }

    private fun observe() = with(viewModelScope) {
        launch {
            observeFoodsUseCase().collect {
                screenState = screenState.copy(
                    error = null,
                    foods = it
                )
            }
        }
    }

    fun onFoodSelection(food: Food) {
        screenState = screenState.copy(
            selectedFoodId = food.id.takeIf { food.id != screenState.selectedFoodId }
        )
    }

    fun loadNextPage() {
        viewModelScope.launch {
            observeFoodsUseCase.loadNextPage()
        }
    }

}
