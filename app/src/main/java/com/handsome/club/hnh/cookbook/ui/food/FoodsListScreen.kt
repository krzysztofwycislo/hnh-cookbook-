package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import com.handsome.club.hnh.cookbook.ui.fepSignatureColors
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.random.Random

// TODO to remove
fun createExampleFood(seed: Int): Food {
    val ingredients = IntStream.rangeClosed(1, seed)
        .mapToObj { Ingredient("ingredient$seed-$it", 100) }
        .collect(Collectors.toList())

    val feps = IntStream.rangeClosed(1, seed)
        .mapToObj { Fep(randomRealFepName(), it.toFloat()) }
        .collect(Collectors.toList())

    return Food("food$seed", "resource$seed", seed.toFloat(), seed * 200, ingredients, feps)
}

fun randomRealFepName(): String {
    val fepKey = fepSignatureColors.keys.toList()[Random.nextInt(fepSignatureColors.size)]
    return if (Random.nextBoolean()) {
        "$fepKey +2"
    } else {
        "$fepKey +1"
    }
}

@Composable
fun FoodsListScreen() {
    val foods = remember {
        IntStream.rangeClosed(1, 10)
            .mapToObj(::createExampleFood)
            .collect(Collectors.toList())
    }

    Column {
        Text(text = stringResource(id = R.string.foods_list_title))
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(foods) {
                FoodItemView(it)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FoodsListScreenPrev() {
    FoodsListScreen()
}