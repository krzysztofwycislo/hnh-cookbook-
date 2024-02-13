package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.FoodFilters
import com.handsome.club.hnh.cookbook.model.food.ObserveFoodsUseCase
import com.handsome.club.hnh.cookbook.model.food.ToggleFavoriteFoodUseCase
import com.handsome.club.hnh.cookbook.ui.base.BaseViewModel
import com.handsome.club.hnh.cookbook.ui.base.ScreenError
import com.handsome.club.hnh.cookbook.ui.base.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
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
    private val toggleFavoriteFoodUseCase: ToggleFavoriteFoodUseCase
) : BaseViewModel() {

    var screenState by mutableStateOf(FoodListScreenState())
        private set

    private val filters = MutableStateFlow(FoodFilters.DEFAULT)


    init {
        observe()
    }

    private fun observe() = with(viewModelScope) {
        launch {
            filters.flatMapMerge {
                observeFoodsUseCase(it)
            }.collect {
                // TODO refactor favorite collection
                screenState = screenState.copy(
                    error = null,
                    foods = it
                )
            }
        }
    }

    fun onFoodSelection(selectedFood: Food) {
        screenState = screenState.copy(
            selectedFoodId = selectedFood.id.takeIf { selectedFood.id != screenState.selectedFoodId }
        )
    }

    fun toggleFavorite(food: Food) {
        viewModelScope.launch {
            toggleFavoriteFoodUseCase(food.id)
        }
    }

    fun applyFilters(newFilters: FoodFilters) {
        viewModelScope.launch {
            filters.emit(newFilters)
        }
    }

    private var loadPageJob: Job? = null

    fun loadNextPage() {
        if (loadPageJob?.isActive == true) return

        loadPageJob = viewModelScope.launch {
            observeFoodsUseCase.loadNextPage()
        }
    }

}
