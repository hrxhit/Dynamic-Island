package com.example.myisland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myisland.ui.settings.SettingsScreen
import com.example.myisland.ui.theme.MyIslandTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyIslandTheme {
                SettingsScreen()
            }
        }
    }
}
