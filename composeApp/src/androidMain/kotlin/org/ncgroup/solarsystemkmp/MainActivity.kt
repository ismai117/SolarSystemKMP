package org.ncgroup.solarsystemkmp

import App
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class SolarSystemKMPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
    companion object {
        lateinit var INSTANCE: SolarSystemKMPApp
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}