package com.handsome.club.hnh.cookbook.base.paging

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PageLazyColumn(
    loadPage: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyListScope.() -> Unit,
) {
    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        content = {
            content()
            item {
                LaunchedEffect(true) { loadPage() }
            }
        },
        state = scrollState,
        contentPadding = contentPadding
    )
}