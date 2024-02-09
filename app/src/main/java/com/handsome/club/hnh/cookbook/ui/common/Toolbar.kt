package com.handsome.club.hnh.cookbook.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.handsome.club.hnh.cookbook.R


@ExperimentalMaterial3Api
@Composable
fun MainToolbar(
    title: String,
    onFiltersClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = stringResource(id = R.string.open_filters_button),
                modifier = Modifier.clickable {
                    onFiltersClick()
                }
            )
        }
    )
}

@Preview
@Composable
fun MainToolbarPrev() {
    MaterialTheme {
        MainToolbar(
            title = "Toolbar title",
            onFiltersClick = {}
        )
    }
}