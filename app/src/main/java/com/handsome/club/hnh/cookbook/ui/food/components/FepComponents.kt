package com.handsome.club.hnh.cookbook.ui.food.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.base.calculations.getRemainder
import com.handsome.club.hnh.cookbook.model.fep.FepType
import com.handsome.club.hnh.cookbook.model.food.Fep
import com.handsome.club.hnh.cookbook.ui.FoodMocks
import com.handsome.club.hnh.cookbook.ui.base.withAlpha
import com.handsome.club.hnh.cookbook.ui.theme.HavenHearthCookbookTheme
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerXXS
import com.handsome.club.hnh.cookbook.ui.theme.determineFepSignatureColor
import kotlin.reflect.KClass

private const val FEPS_ROW_SIZE = 5

@Composable
fun FepsList(
    feps: List<Fep>,
    itemsInRow: Int = FEPS_ROW_SIZE
) {
    val groupedFeps = feps
        .groupBy { it.type::class }
        .toList()
        .sortedBy { -it.second.size }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant.withAlpha(.5f))
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        maxItemsInEachRow = itemsInRow
    ) {
        repeat(groupedFeps.size) { index ->
            val currentFep = groupedFeps[index]
            FepIndicator(
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .weight(1f),
                typeClass = currentFep.first,
                feps = currentFep.second,
            )
        }

        repeat(groupedFeps.size.getRemainder(itemsInRow)) {
            Box(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun FepIndicator(
    modifier: Modifier,
    typeClass: KClass<out FepType>,
    feps: List<Fep>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth(.60f)
                .aspectRatio(1f)
                .background(typeClass.determineFepSignatureColor())
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = requireNotNull(typeClass.simpleName).take(3),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        feps.sortedBy { it.type.statReward }.forEachIndexed { index, fep ->
            val fepText = when {
                fep.value > 999 -> "+999"
                fep.value >= 100 -> String.format("%.0f", fep.value)
                else -> String.format("%.2f", fep.value)
            }

            VertSpacerXXS()
            Text(
                text = fepText,
                fontWeight = FontWeight.Bold.takeIf { index > 0 }
            )
        }
    }
}

@Preview
@Composable
fun FepsListPrev() = with(FoodMocks) {
    HavenHearthCookbookTheme {
        FepsList(feps = exampleFeps)
    }
}