package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import com.handsome.club.hnh.cookbook.ui.createExampleFood
import com.handsome.club.hnh.cookbook.ui.determineFepSignatureColors

private const val imagesUrl = ""

@Composable
fun FoodListItem(food: Food, onClick: (Food) -> Unit, showIngredients: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick(food) },
                indication = rememberRipple(bounded = true, color = Color.LightGray, radius = 300.dp),
            )
    ) {
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

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = food.itemName)
                    Spacer(modifier = Modifier.height(8.dp))
                    FepsSimpleDisplay(food.feps)
                }

                Icon(
                    painter = painterResource(
                        id = if (showIngredients) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up,
                    ),
                    contentDescription = "",
                    tint = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                showIngredients,

                // Sets the initial height of the content to 20, revealing only the top of the content at
                // the beginning of the expanding animation.
                enter = expandVertically(expandFrom = Alignment.Top) { 20 },

                // Shrinks the content to half of its full height via an animation.
                exit = shrinkVertically(animationSpec = tween()) { 20 },
            ) {
                IngredientsSimpleDisplay(food.ingredients)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.LightGray)
                    .alpha(0.3f)
            )
        }
    }
}

@Composable
fun IngredientsSimpleDisplay(ingredients: List<Ingredient>) {
    Column(Modifier.fillMaxWidth()) {

        ingredients.forEach {
            Row {
                Text(text = "${it.percentage}%")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = it.name)
            }

            Spacer(modifier = Modifier.height(16.dp))
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

@Composable
@Preview(showBackground = true)
fun FoodItemViewPrev() {
    FoodListItem(createExampleFood(10), {}, true)
}