package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    val selectedFoodId: String? = null,
) : ScreenState

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val observeFoodsUseCase: ObserveFoodsUseCase,
    private val toggleFavoriteFoodUseCase: ToggleFavoriteFoodUseCase
) : BaseViewModel() {

    var state by mutableStateOf(FoodListScreenState())
        private set

    val foods = mutableStateListOf<Food>()

    private val filters = MutableStateFlow(FoodFilters.DEFAULT)


    init {
        observe()
    }

    fun <T> SnapshotStateList<T>.updateElementState(
        predicate: (T) -> Boolean,
        updateBlock: (T) -> T
    ) {
        val index = indexOfFirst(predicate)
        add(index, updateBlock(get(index)))
    }

    private fun observe() = with(viewModelScope) {
        launch {
            filters.flatMapMerge {
                observeFoodsUseCase(it)
            }.collect {
                foods.addAll(it)
                state = state.copy(error = null)
            }
        }
    }

    fun onFoodSelection(selectedFood: Food) {
        state = state.copy(
            selectedFoodId = selectedFood.id.takeIf { selectedFood.id != state.selectedFoodId }
        )
    }

    fun toggleFavorite(food: Food) {
        viewModelScope.launch {
            val toggleResult = toggleFavoriteFoodUseCase(food.id)

            foods.updateElementState(
                { it.id == food.id }
            ) {
                it.copy(isFavorite = toggleResult)
            }
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
