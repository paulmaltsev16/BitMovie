package com.paulmaltsev.bitmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.paulmaltsev.bitmovie.core.managers.ConnectionStatusManager
import com.paulmaltsev.bitmovie.core.managers.ConnectionStatusManagerImpl
import com.paulmaltsev.bitmovie.core.navigation.AppNavigation
import com.paulmaltsev.bitmovie.core.ui.theme.BitMovieTheme

class MainActivity : ComponentActivity() {

    private val connectionStatusManager: ConnectionStatusManager by lazy {
        ConnectionStatusManagerImpl(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val connectionStatus by connectionStatusManager.observe().collectAsState(
                initial = ConnectionStatusManager.Status.LOST
            )
            BitMovieTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (connectionStatus == ConnectionStatusManager.Status.LOST) {
                        LostConnectionMessage()
                    }
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun LostConnectionMessage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(2f)
    ) {
        Text(
            text = stringResource(id = R.string.connection_lost),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Red.copy(alpha = 0.3f)),
        )
    }
}