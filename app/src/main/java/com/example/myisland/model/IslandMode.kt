package com.example.myisland.model

import androidx.compose.ui.graphics.Color

sealed interface IslandMode {
    val accentColor: Color

    data class Notification(
        val appName: String,
        val appIconRes: Int? = null,
        val title: String,
        val message: String,
        override val accentColor: Color = Color(0xFFD84315)
    ) : IslandMode

    data class Music(
        val trackTitle: String,
        val artist: String,
        val isPlaying: Boolean,
        val progress: Float,
        override val accentColor: Color = Color(0xFF1E88E5)
    ) : IslandMode

    data class Timer(
        val remainingMillis: Long,
        val isRunning: Boolean,
        override val accentColor: Color = Color(0xFFFFB300)
    ) : IslandMode

    data class Call(
        val callerName: String,
        val duration: String,
        val isActive: Boolean,
        override val accentColor: Color = Color(0xFF43A047)
    ) : IslandMode

    data class Battery(
        val percentage: Int,
        val isCharging: Boolean,
        override val accentColor: Color = if (percentage <= 20 && !isCharging) Color.Red else Color(0xFF43A047)
    ) : IslandMode

    data class Default(
        override val accentColor: Color = Color.Transparent
    ) : IslandMode
}
