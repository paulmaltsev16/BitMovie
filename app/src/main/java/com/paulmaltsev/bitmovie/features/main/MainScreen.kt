package com.paulmaltsev.bitmovie.features.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.paulmaltsev.bitmovie.core.navigation.AppBottomNavigationBar
import com.paulmaltsev.bitmovie.core.navigation.AppNavigationGraph
import com.paulmaltsev.bitmovie.core.navigation.AppScreens

private val rootBottomBarScreens = listOf(
    AppScreens.Home,
    AppScreens.Favorites,
    AppScreens.Menu
)

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(
                navController = navController,
                screens = rootBottomBarScreens
            )
        }
    ) {
        AppNavigationGraph(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}