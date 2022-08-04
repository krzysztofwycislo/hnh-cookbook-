package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.base.ErrorScreen
import com.handsome.club.hnh.cookbook.ui.base.LoadingScreen
import com.handsome.club.hnh.cookbook.ui.exampleFoods


@Composable
fun FoodsListScreen(viewModel: FoodListViewModel) {
    val state = viewModel.screenState

    Column {
        when {
            state.initialLoading -> LoadingScreen()
            state.error != null -> ErrorScreen(state.error)
            state.foods != null -> FoodsList(state.foods, viewModel::onFoodSelection, state.selectedFoodId)
        }
    }
}

@Composable
fun FoodsList(foods: List<Food>, onClick: (Food) -> Unit, selectedFoodId: Long?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(foods) {
                val isSelected = it.id == selectedFoodId
                FoodListItem(it, onClick, isSelected)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FoodsListScreenPrev() {
    FoodsList(exampleFoods, {}, 1)
}