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
import com.handsome.club.hnh.cookbook.ui.createExampleFood


@Composable
fun FoodsListScreen(viewModel: FoodListViewModel) {
    val state = viewModel.screenState

    Column {
        when {
            state.initialLoading -> LoadingScreen()
            state.error != null -> ErrorScreen(state.error)
            state.foods != null -> FoodsList(state.foods)
        }
    }
}

@Composable
fun FoodsList(foods: List<Food>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(foods) {
                FoodItemView(it)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FoodsListScreenPrev() {
    FoodsList(
        listOf(
            createExampleFood(1),
            createExampleFood(2),
            createExampleFood(3),
            createExampleFood(4),
        )
    )
}