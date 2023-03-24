package com.handsome.club.hnh.cookbook.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.handsome.club.hnh.cookbook.ui.base.ErrorScreen
import com.handsome.club.hnh.cookbook.ui.base.LoadingScreen
import com.handsome.club.hnh.cookbook.ui.food.FoodListViewModel
import com.handsome.club.hnh.cookbook.ui.food.FoodsListScreen
import com.handsome.club.hnh.cookbook.ui.theme.HavenHearthCookbookTheme


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.screenState

    HavenHearthCookbookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {

            when {
                state.initialLoading -> LoadingScreen()
                state.error != null -> ErrorScreen(state.error)
                else -> MainNavigationScreen()
            }

        }
    }
}

@Composable
fun MainNavigationScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "foodsList") {
        composable("foodsList") {
            val viewModel = hiltViewModel<FoodListViewModel>()
            FoodsListScreen(viewModel)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}