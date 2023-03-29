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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.base.paging.PageLazyColumn
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.FoodMocks
import com.handsome.club.hnh.cookbook.ui.base.ErrorScreen
import com.handsome.club.hnh.cookbook.ui.common.MainToolbar
import com.handsome.club.hnh.cookbook.ui.theme.HavenHearthCookbookTheme
import kotlinx.coroutines.launch


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

@Composable
fun FoodsList(
    foods: List<Food>,
    onClick: (Food) -> Unit,
    selectedFoodId: Long?,
    loadPage: () -> Unit
) {
    val listState = rememberLazyListState()

    val scrollToTopEnabled: Boolean by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Scaffold(
        topBar = { MainToolbar("Brodgar's Recipes") },
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
                    val isSelected = it.id == selectedFoodId
                    FoodListItem(it, onClick, isSelected)
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
            selectedFoodId = 1,
            loadPage = {}
        )
    }
}