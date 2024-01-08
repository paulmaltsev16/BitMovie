package com.paulmaltsev.bitmovie.core.ui.views

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.paulmaltsev.bitmovie.core.ui.dialog.AppLoaderDialog
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AppWebView(stringUrl: String, onCloseButtonClick: VoidCallback) {
    val isDarkMode = isSystemInDarkTheme()
    AndroidView(
        factory = { context ->
            val dialog = AppLoaderDialog(isDarkMode, context) { _, _ ->
                onCloseButtonClick()
            }
            dialog.show()

            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()

                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.setSupportZoom(true)
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, progress: Int) {
                        if (progress >= 100) {
                            dialog.dismiss()
                        }
                    }
                }
            }
        },
        update = { webView ->
            webView.loadUrl(stringUrl)
        }
    )
}

