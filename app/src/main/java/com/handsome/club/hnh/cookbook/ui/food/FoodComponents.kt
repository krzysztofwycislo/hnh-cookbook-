package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.createExampleFood
import com.handsome.club.hnh.cookbook.ui.determineFepSignatureColors

private const val imagesUrl = ""

@Composable
fun FoodItemView(food: Food) {
    Row(modifier = Modifier.fillMaxWidth()) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(imagesUrl + food.resourceName)
//                .crossfade(true)
//                .build(),
//            contentDescription = stringResource(R.string.food_image),
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .clip(MaterialTheme.shapes.small)
//                .background(MaterialTheme.colors.background)
//        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = food.itemName)
            FepsSimpleDisplay(food.feps)
        }
    }
}

@Composable
fun FepsSimpleDisplay(feps: List<Fep>) {
    Row {
        feps.forEach { fep ->
            Text(
                text = fep.value.toString(),
                color = determineFepSignatureColors(fep),
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemViewPrev() {
    FoodItemView(createExampleFood(10))
}