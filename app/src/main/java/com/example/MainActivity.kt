package com.example

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameWebViewScreen()
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GameWebViewScreen() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                
                // Configure high-performance web runtime properties for Canvas games
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                
                // Lock mobile zoom behaviors to prevent accidental scaling during touch controls
                settings.useWideViewPort = true
                settings.loadWithOverviewMode = true
                settings.setSupportZoom(false)
                settings.builtInZoomControls = false
                settings.displayZoomControls = false
                
                // Enable rich hardware acceleration properties if available
                setLayerType(WebView.LAYER_TYPE_HARDWARE, null)
                
                // Load full game programmatically from local Android app assets
                loadUrl("file:///android_asset/index.html")
            }
        }
    )
}
