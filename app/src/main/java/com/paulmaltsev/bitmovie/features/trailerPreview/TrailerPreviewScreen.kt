package com.paulmaltsev.bitmovie.features.trailerPreview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.data.constants.Constants.Companion.YOU_TUBE_BASE_PREVIEW_URL
import com.paulmaltsev.bitmovie.core.ui.views.AppTopBar
import com.paulmaltsev.bitmovie.core.ui.views.AppWebView
import com.paulmaltsev.bitmovie.features.trailerPreview.viewModel.TrailerPreviewViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailerPreviewScreen(
    movieId: Int,
    navController: NavHostController
) {
    val viewModel = viewModel<TrailerPreviewViewModel>()
    viewModel.getFirstYoutubeVideoKey(movieId.toString())
    val youTubeKey = viewModel.youTubeKey.collectAsStateWithLifecycle(initialValue = "").value

    Scaffold(
        topBar = AppTopBar(title = stringResource(R.string.preview)) {
            navController.popBackStack()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            AppWebView(
                stringUrl = String.format(YOU_TUBE_BASE_PREVIEW_URL, youTubeKey),
                onCloseButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}