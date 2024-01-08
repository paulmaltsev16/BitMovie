package com.paulmaltsev.bitmovie.core.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.utils.ComposableVoidCallback

@Composable
fun AppBottomNavigationBar(
    navController: NavController,
    screens: List<AppScreens>
): ComposableVoidCallback = {
    var selectedItem by remember { mutableStateOf(0) }
    var currentRoute by remember { mutableStateOf(AppScreens.Home.route) }

    screens.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        screens.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = NavigationIcon(item.icon!!),
                label = NavigationTitle(stringResource(item.titleId!!)),
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
private fun NavigationIcon(imageVector: ImageVector): ComposableVoidCallback = {
    Icon(
        imageVector = imageVector,
        contentDescription = stringResource(id = R.string.content_description_bottom_navigation_icon)
    )
}

@Composable
private fun NavigationTitle(title: String): ComposableVoidCallback = {
    Text(text = title)
}