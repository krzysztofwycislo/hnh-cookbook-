package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.base.paging.PageLazyColumn
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.base.ErrorScreen
import com.handsome.club.hnh.cookbook.ui.common.MainToolbar
import com.handsome.club.hnh.cookbook.ui.exampleFoods


@Composable
fun FoodsListScreen(viewModel: FoodListViewModel) {
    val state = viewModel.screenState

    Column {
        when {
            state.error != null ->
                ErrorScreen(
                    error = state.error
                )

            state.foods != null ->
                FoodsList(
                    foods = state.foods,
                    onClick = viewModel::onFoodSelection,
                    selectedFoodId = state.selectedFoodId,
                    loadPage = viewModel::loadNextPage
                )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodsList(
    foods: List<Food>,
    onClick: (Food) -> Unit,
    selectedFoodId: Long?,
    loadPage: () -> Unit
) {
    Scaffold(
        topBar = { MainToolbar("H&H Cookbook") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PageLazyColumn(
                modifier = Modifier.fillMaxWidth(),
                loadPage = loadPage,
            ) {

                stickyHeader {
                    FepsStickyHeader(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                items(foods) {
                    val isSelected = it.id == selectedFoodId
                    FoodListItem(it, onClick, isSelected)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FoodsListScreenPrev() {
    FoodsList(
        foods = exampleFoods,
        onClick = {},
        selectedFoodId = 1,
        loadPage = {}
    )
}