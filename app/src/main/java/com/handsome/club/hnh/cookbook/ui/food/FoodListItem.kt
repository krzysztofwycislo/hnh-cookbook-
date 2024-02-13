package com.handsome.club.hnh.cookbook.ui.food

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.handsome.club.hnh.cookbook.R
import com.handsome.club.hnh.cookbook.model.food.Food
import com.handsome.club.hnh.cookbook.ui.FoodMocks
import com.handsome.club.hnh.cookbook.ui.base.withAlpha
import com.handsome.club.hnh.cookbook.ui.food.components.FepsList
import com.handsome.club.hnh.cookbook.ui.food.components.IngredientsList
import com.handsome.club.hnh.cookbook.ui.theme.HavenHearthCookbookTheme
import com.handsome.club.hnh.cookbook.ui.theme.HorSpacerM
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerM
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerXS

private const val imagesUrl = "https://www.havenandhearth.com/mt/r/"

@Composable
fun FoodListItem(
    food: Food,
    onClick: (Food) -> Unit,
    toggleFavorite: (Food) -> Unit,
    ingredientsVisible: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick(food) },
                indication = rememberRipple(bounded = true, color = Color.LightGray),
            )
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        FoodHeader(
            food = food,
            toggleFavorite = toggleFavorite
        )

        VertSpacerM()

        FepsList(
            feps = food.sortedFeps
        )

        VertSpacerXS()

        AnimatedVisibility(
            ingredientsVisible,
            enter = expandVertically(
                expandFrom = Alignment.Top,
                animationSpec = tween()
            ) { 20 },
            exit = shrinkVertically(
                shrinkTowards = Alignment.Top,
                animationSpec = tween()
            ) { 20 },
        ) {
            IngredientsList(food.ingredients)
        }

        FoodFooter(food, ingredientsVisible)
    }
}

@Composable
fun FoodHeader(
    food: Food,
    toggleFavorite: (Food) -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO image loader
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imagesUrl + food.resourceName)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.food_image),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxHeight(),
        )

        HorSpacerM()

        Text(text = food.name, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.weight(1f))

        val favoriteIconId = R.drawable.ic_favorite

        Icon(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { toggleFavorite(food) },
                    indication = rememberRipple(bounded = false),
                ),
            painter = painterResource(id = favoriteIconId),
            contentDescription = stringResource(id = R.string.favorite_icon),
            tint = MaterialTheme.colorScheme.secondary.withAlpha(if (food.isFavorite) .8f else .3f)
        )
    }
}

@Composable
fun FoodFooter(
    food: Food,
    ingredientsVisible: Boolean,
    color: Color = MaterialTheme.colorScheme.outline.withAlpha(.5f),
    dividerHeight: Dp = 2.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(dividerHeight)
                .background(color),
        )

        if (food.ingredients.any()) {
            val arrowId = if (ingredientsVisible) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down

            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = arrowId),
                contentDescription = stringResource(id = R.string.expand_icon),
                tint = color
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(dividerHeight)
                .background(color),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FoodItemViewPrev() = with(FoodMocks) {
    HavenHearthCookbookTheme {
        FoodListItem(
            food = createExampleFood(10),
            ingredientsVisible = true,
            onClick = {},
            toggleFavorite = {}
        )
    }
}