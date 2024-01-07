package com.paulmaltsev.bitmovie.core.extensions

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.paulmaltsev.bitmovie.R

@Composable
fun Modifier.appPadding(paddingId: Int = R.dimen.padding_standard): Modifier {
    return padding(dimensionResource(id = paddingId))
}