package com.example.myisland.ui.expanded

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myisland.model.IslandMode

@Composable
fun IslandPillView() {
    var isExpanded by remember { mutableStateOf(false) }
    var currentMode by remember { mutableStateOf<IslandMode>(IslandMode.Default()) }

    // Mock switching modes for preview/testing
    LaunchedEffect(Unit) {
        // In a real app, this would observe the ViewModel's StateFlow
    }

    val width by animateDpAsState(
        targetValue = if (isExpanded) 340.dp else 120.dp,
        animationSpec = spring(
            dampingRatio = 0.72f,
            stiffness = 520f
        ), label = "width_anim"
    )

    val height by animateDpAsState(
        targetValue = if (isExpanded) 160.dp else 36.dp,
        animationSpec = spring(
            dampingRatio = 0.72f,
            stiffness = 520f
        ), label = "height_anim"
    )

    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(if (isExpanded) 24.dp else 18.dp))
            .background(Color.Black)
            .clickable { isExpanded = !isExpanded },
        contentAlignment = Alignment.Center
    ) {
        if (isExpanded) {
            ExpandedContent(currentMode)
        } else {
            CollapsedContent(currentMode)
        }
    }
}

@Composable
fun CollapsedContent(mode: IslandMode) {
    Row(
        modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Mock icons for collapsed state
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "Message",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ExpandedContent(mode: IslandMode) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("Messages", color = Color.White, style = MaterialTheme.typography.titleMedium)
                Text("New message from John", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
