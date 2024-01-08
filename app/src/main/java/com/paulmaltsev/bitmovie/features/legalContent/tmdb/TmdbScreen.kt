package com.paulmaltsev.bitmovie.features.legalContent.tmdb

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.ui.views.AppSpacer
import com.paulmaltsev.bitmovie.core.ui.views.AppTopBar

/**
 * According to the documentation, TMDB provides dates for free. The service only requires
 * contribution it as a data source.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TmdbScreen(navController: NavController) {
    Scaffold(
        topBar = AppTopBar(title = stringResource(R.string.the_movie_database)) {
            navController.popBackStack()
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .appPadding()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painterResource(id = R.drawable.logo_tmdb),
                stringResource(R.string.content_description_tmdb_logo)
            )
            AppSpacer()
            Text(
                text = stringResource(id = R.string.data_provided_by_tmdb),
                fontStyle = FontStyle.Italic
            )
            AppSpacer()
            Text(
                text = stringResource(id = R.string.tmdb_intro),
                fontWeight = FontWeight.Bold
            )
        }
    }
}