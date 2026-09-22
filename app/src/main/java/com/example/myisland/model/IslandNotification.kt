package com.example.myisland.model

import android.app.PendingIntent
import android.graphics.Bitmap

data class IslandNotification(
    val key: String,
    val packageName: String,
    val appName: String,
    val title: String,
    val text: String,
    val iconBitmap: Bitmap? = null,
    val contentIntent: PendingIntent? = null
)
