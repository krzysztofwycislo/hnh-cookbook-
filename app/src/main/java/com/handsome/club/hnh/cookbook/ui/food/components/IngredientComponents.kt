package com.handsome.club.hnh.cookbook.ui.food.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.handsome.club.hnh.cookbook.model.food.Ingredient
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerM
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerXS


@Composable
fun IngredientsList(ingredients: List<Ingredient>) {
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