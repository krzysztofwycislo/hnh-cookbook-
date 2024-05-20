package com.handsome.club.hnh.cookbook.ui.base

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.handsome.club.hnh.cookbook.R

sealed class ScreenError(
    @StringRes val messageId: Int,
    @DrawableRes val errorImageId: Int
) {

    data object DataLoadingFailed : ScreenError(R.string.data_loading_failed_message, R.drawable.error_outline_black)

}

@Composable
fun ErrorScreen(error: ScreenError) {
    when (error) {
        ScreenError.DataLoadingFailed -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Image(
                    painterResource(error.errorImageId),
                    stringResource(error.messageId),
                    modifier = Modifier.size(80.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = stringResource(error.messageId))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPrev() {
    ErrorScreen(ScreenError.DataLoadingFailed)
}
