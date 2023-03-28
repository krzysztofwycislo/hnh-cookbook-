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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.model.fep.Fep
import com.handsome.club.hnh.cookbook.model.fep.FepType
import com.handsome.club.hnh.cookbook.ui.theme.VertSpacerXXS
import com.handsome.club.hnh.cookbook.ui.theme.determineFepSignatureColor
import kotlin.reflect.KClass

private const val FEPS_ROW_SIZE = 5

@ExperimentalLayoutApi
@Composable
fun FepsList(
    feps: List<Fep>,
    itemsInRow: Int = FEPS_ROW_SIZE
) {
    val groupedFeps = feps
        .groupBy { it.type::class }
        .toList()
        .sortedBy { -it.second.size }

    val allItemsCount = feps.size + itemsInRow - feps.size.mod(itemsInRow)

    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        maxItemsInEachRow = itemsInRow
    ) {
        repeat(allItemsCount) { index ->
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                groupedFeps.getOrNull(index)
                    ?.let { FepIndicator(it.first, it.second) }
            }
        }
    }
}

@Composable
private fun FepIndicator(typeClass: KClass<out FepType>, feps: List<Fep>) {
    Column(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .width(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth()
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
            VertSpacerXXS()
            Text(
                text = String.format("%.2f", fep.value),
                fontWeight = FontWeight.Bold.takeIf { index > 0 }
            )
        }
    }
}