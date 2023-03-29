package com.handsome.club.hnh.cookbook.ui.food.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import com.handsome.club.hnh.cookbook.ui.theme.HorSpacerXS
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerXS


@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        ingredients.sortedBy { -it.percentage }
            .forEach {

                VertSpacerXS()

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    )

                    HorSpacerXS()

                    Text(text = "${it.name}:")

                    HorSpacerXS()

                    Text(text = "${it.percentage}%")
                }

                VertSpacerXS()

            }
    }
}