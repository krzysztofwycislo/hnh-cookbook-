package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.determineFepSignatureColors

private const val imagesUrl = ""

@Composable
fun FoodItemView(food: Food) {
    Row {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imagesUrl + food.resourceName)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.food_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .background(MaterialTheme.colors.background)
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = food.itemName)
            FepsSimpleDisplay(food.feps)

            Spacer(modifier = Modifier.height(16.dp))
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
    FoodItemView(createExampleFood(4))
}