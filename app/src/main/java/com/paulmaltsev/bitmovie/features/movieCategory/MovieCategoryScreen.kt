package com.paulmaltsev.bitmovie.features.movieCategory

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.ui.views.AppTopBar
import com.paulmaltsev.bitmovie.features.home.components.MovieCollectionTitle

@Composable
fun MovieCategoryScreen(
    screenTitleId: Int,
    navController: NavController,
) {
    Scaffold(
        topBar = AppTopBar(title = stringResource(id = R.string.category)) {
            navController.popBackStack()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .appPadding()
        ) {
            item {
                MovieCollectionTitle(name = stringResource(id = screenTitleId))
            }
        }
    }
}