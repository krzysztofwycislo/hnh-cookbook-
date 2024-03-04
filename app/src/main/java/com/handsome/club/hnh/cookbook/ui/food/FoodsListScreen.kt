@file:OptIn(ExperimentalMaterial3Api::class)

package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.base.paging.PageLazyColumn
import com.handsome.club.hnh.cookbook.infrastructure.AppDestination
import com.handsome.club.hnh.cookbook.infrastructure.AppNavigation
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.FoodMocks
import com.handsome.club.hnh.cookbook.ui.base.ErrorScreen
import com.handsome.club.hnh.cookbook.ui.common.MainToolbar
import com.handsome.club.hnh.cookbook.ui.theme.HavenHearthCookbookTheme
import kotlinx.coroutines.launch


@Composable
fun FoodsListScreen(
    viewModel: FoodListViewModel,
    navigation: AppNavigation
) {
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
                    selectedFoodId = state.selectedFoodId,
                    onClick = viewModel::onFoodSelection,
                    loadPage = viewModel::loadNextPage,
                    toggleFavorite = viewModel::toggleFavorite,
                    onFiltersClick = {
                        navigation.navigateTo(
                            AppDestination.FoodFilterScreen(viewModel::applyFilters)
                        )
                    }
                )
        }
    }
}

@Composable
fun FoodsList(
    foods: List<Food>,
    onClick: (Food) -> Unit,
    selectedFoodId: String?,
    loadPage: () -> Unit,
    toggleFavorite: (Food) -> Unit,
    onFiltersClick: () -> Unit
) {
    val listState = rememberLazyListState()

    val scrollToTopEnabled: Boolean by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        topBar = {
            MainToolbar(
                title = "Brodgar's Recipes",
                onFiltersClick = onFiltersClick
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = scrollToTopEnabled,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                NavigateToTopFab {
                    listState.animateScrollToItem(0)
                }
            }
        }
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
                state = listState
            ) {
                items(foods) {
                    val isSelected = remember(selectedFoodId) { it.id == selectedFoodId }

                    FoodListItem(
                        food = it,
                        ingredientsVisible = isSelected,
                        onClick = onClick,
                        toggleFavorite = toggleFavorite
                    )
                }
            }
        }
    }
}

@Composable
fun NavigateToTopFab(onClick: suspend () -> Unit) {
    val scope = rememberCoroutineScope()

    FloatingActionButton(
        modifier = Modifier.clip(CircleShape),
        onClick = {
            scope.launch {
                onClick()
            }
        },
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_move_up),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = null
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun FoodsListScreenPrev() = with(FoodMocks) {
    HavenHearthCookbookTheme {
        FoodsList(
            foods = exampleFoods,
            onClick = {},
            selectedFoodId = "1",
            loadPage = {},
            toggleFavorite = {},
            onFiltersClick = {},
        )
    }
}