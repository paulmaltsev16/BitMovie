package com.paulmaltsev.bitmovie.core.ui.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.utils.ComposableVoidCallback
import com.paulmaltsev.bitmovie.core.utils.VoidCallback


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onBackButtonClick: VoidCallback
): ComposableVoidCallback {
    return {
        TopAppBar(
            title = {
                Text(text = title)
            },
            navigationIcon = {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.content_description_go_back_icon)
                    )
                }
            }
        )
    }
}