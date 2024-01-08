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
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.utils.ComposableVoidCallback

@Composable
fun AppBottomNavigationBar(
    navController: NavController,
    screens: List<AppScreens>
) {
    var selectedItem by remember { mutableStateOf(0) }

    // Looking for the selectedItem according to the last destination route.
    val navBackStackEntry by (navController.currentBackStackEntryAsState())
    val currentRoute = navBackStackEntry?.destination?.route
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