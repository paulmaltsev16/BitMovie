package com.paulmaltsev.bitmovie.core.ui.views

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppSpacer(space: Dp = 16.dp) {
    Spacer(modifier = Modifier.height(space))
}

