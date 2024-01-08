package com.paulmaltsev.bitmovie.core.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.paulmaltsev.bitmovie.R

@Composable
fun AppPlaceholder(title: String = stringResource(R.string.something_went_wrong_please_try_later)) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}