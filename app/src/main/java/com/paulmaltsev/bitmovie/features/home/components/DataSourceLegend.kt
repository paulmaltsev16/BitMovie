package com.paulmaltsev.bitmovie.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@Composable
fun DataSourceLegend(onClick: VoidCallback) {
    Text(
        stringResource(id = R.string.data_provided_by_tmdb),
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .appPadding()
            .clickable(onClick = onClick)
    )
}