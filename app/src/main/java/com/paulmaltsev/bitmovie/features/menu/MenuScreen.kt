package com.paulmaltsev.bitmovie.features.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.extensions.appPadding
import com.paulmaltsev.bitmovie.core.navigation.AppScreens
import com.paulmaltsev.bitmovie.core.ui.views.AppButton
import com.paulmaltsev.bitmovie.core.ui.views.AppSpacer

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .appPadding()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher),
                contentDescription = stringResource(R.string.content_description_app_logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .align(Alignment.CenterHorizontally)
            )

            AppSpacer()

            Text(text = stringResource(R.string.app_intro))
        }

        AppButton(
            title = "Explore data source",
            modifier = Modifier
                .fillMaxWidth()
        ) {
            navController.navigate(AppScreens.TmdbLegalContent.route)
        }
    }
}