@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.model.fep.Fep
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import com.handsome.club.hnh.cookbook.ui.createExampleFood
import com.handsome.club.hnh.cookbook.ui.theme.*

private const val imagesUrl = "https://www.havenandhearth.com/mt/r/"

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

        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(imagesUrl + food.resourceName)
                                .crossfade(true)
                                .build(),
                            contentDescription = stringResource(R.string.food_image),
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxHeight()
                        )

                        HorSpacerM()

                        Text(text = food.itemName, style = MaterialTheme.typography.titleLarge)
                    }

                    VertSpacerM()

                    FepsSimpleDisplay(food.sortedFeps)
                }

            }

            VertSpacerM()

            AnimatedVisibility(
                showIngredients,
                enter = expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = tween()
                ) { 20 },
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = tween()
                ) { 20 },
            ) {
                IngredientsSimpleDisplay(food.ingredients)
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.LightGray.copy(alpha = .3f)),
                )

                if (food.ingredients.any()) {
                    Icon(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .size(32.dp),
                        painter = painterResource(
                            id = if (showIngredients) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down,
                        ),
                        contentDescription = "",
                        tint = Color.LightGray.copy(alpha = .3f)
                    )
                }
            }
        }
    }
}

@Composable
fun IngredientsSimpleDisplay(ingredients: List<Ingredient>) {
    Column(Modifier.fillMaxWidth()) {

        ingredients.forEach {
            Row {
                Text(text = "${it.percentage}%")

                VertSpacerXS()

                Text(text = it.name)
            }

            VertSpacerM()
        }
    }
}

@Composable
fun FepsSimpleDisplay(feps: List<Fep>) {
    val groupedFeps = feps.groupBy { it.type::class }

    val itemsInRow = 5

    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        maxItemsInEachRow = itemsInRow
    ) {
        repeat(feps.size + itemsInRow - feps.size.mod(itemsInRow)) { index ->
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                groupedFeps.values
                    .sortedBy { -it.size }
                    .getOrNull(index)
                    ?.let { FepIndicator(it) }
            }
        }
    }
}

@Composable
fun FepIndicator(feps: List<Fep>) {
    Column(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val someFep = feps.first()

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(someFep.type.determineFepSignatureColor())
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = someFep.type.getShortName().take(3),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        feps.sortedBy { it.type.statReward }.forEachIndexed { index, fep ->
            VertSpacerXXS()
            Text(
                text = String.format("%.2f", fep.value),
                fontWeight = FontWeight.Bold.takeIf { index > 0 }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FoodItemViewPrev() {
    FoodListItem(createExampleFood(10), {}, true)
}