package com.paulmaltsev.bitmovie.core.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.paulmaltsev.bitmovie.R

@Composable
fun AppBottomNavigationBar(
    navController: NavHostController, screens: List<AppScreens>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isBottomBarScreen = screens.any { it.route == currentDestination?.route }
    if (!isBottomBarScreen){
        return
    }

    NavigationBar(
        contentColor = colorResource(id = R.color.main)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: AppScreens, currentDestination: NavDestination?, navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = stringResource(screen.titleId!!))
        },
        icon = {
            Icon(
                imageVector = screen.icon!!,
                contentDescription = stringResource(id = R.string.content_description_bottom_navigation_icon)
            )
        },
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
    )
}


