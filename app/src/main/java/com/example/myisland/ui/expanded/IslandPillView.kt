package com.example.myisland.ui.expanded

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.myisland.model.IslandNotification
import com.example.myisland.repository.NotificationRepository

@Composable
fun IslandPillView(notificationRepository: NotificationRepository) {
    val activeNotif by notificationRepository.activeNotification.collectAsState()
    
    val isExpanded = activeNotif != null
    
    val width by animateDpAsState(
        targetValue = if (isExpanded) 340.dp else 120.dp,
        animationSpec = spring(
            dampingRatio = 0.72f,
            stiffness = 520f
        ), label = "width_anim"
    )

    val height by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 40.dp,
        animationSpec = spring(
            dampingRatio = 0.72f,
            stiffness = 520f
        ), label = "height_anim"
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(height / 2))
            .background(Color.Black)
            .clickable {
                activeNotif?.contentIntent?.send()
                notificationRepository.removeNotification(activeNotif?.key ?: "")
            },
        contentAlignment = Alignment.Center
    ) {
        if (isExpanded) {
            activeNotif?.let { notif ->
                ExpandedContent(notif)
            }
        }
    }
}

@Composable
fun ExpandedContent(notif: IslandNotification) {
    Row(
        modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (notif.iconBitmap != null) {
            Image(
                bitmap = notif.iconBitmap.asImageBitmap(),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        } else {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = if (notif.title.isNotBlank()) notif.title else notif.appName,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1
            )
            if (notif.text.isNotBlank()) {
                Text(
                    text = notif.text,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )
            }
        }
    }
}
