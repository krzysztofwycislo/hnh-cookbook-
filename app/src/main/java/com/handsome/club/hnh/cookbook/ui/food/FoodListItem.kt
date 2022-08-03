package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.createExampleFood
import com.handsome.club.hnh.cookbook.ui.determineFepSignatureColors

private const val imagesUrl = ""

@Composable
fun FoodListItem(food: Food) {
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
            Spacer(modifier = Modifier.height(8.dp))
            FepsSimpleDisplay(food.feps)
        }
    }
}

@Composable
fun FepsSimpleDisplay(feps: List<Fep>) {
    Row {
        feps.forEach { fep ->
            val fepInfo = determineFepSignatureColors(fep)
            Column(
                modifier = Modifier
                    .background(fepInfo.color)
                    .size(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = String.format("%.1f", fep.value))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = fepInfo.shortName)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemViewPrev() {
    FoodListItem(createExampleFood(10))
}