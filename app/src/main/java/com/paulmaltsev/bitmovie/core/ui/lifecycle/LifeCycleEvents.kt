package com.paulmaltsev.bitmovie.core.ui.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

/**
 * Use the RESUMED callback to detect when the Compose onResume state is triggered.
 */
@Composable
fun onResume(voidCallback: VoidCallback) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.RESUMED -> voidCallback()
            else -> {}
        }
    }
}