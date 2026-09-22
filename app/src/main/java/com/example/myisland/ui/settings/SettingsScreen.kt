package com.example.myisland.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    
    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                NavigationBarItem(
                    icon = { Text("STUDIO") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Text("POSITION") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Text("SETTINGS") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (selectedTab) {
                0 -> StudioTab()
                1 -> PositionTab()
                2 -> SettingsTab()
            }
        }
    }
}

@Composable
fun StudioTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Control Studio", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        
        // Master Power Card
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Master Power", style = MaterialTheme.typography.titleMedium, color = Color.White)
                    Text("Enable dynamic island overlay", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Switch(checked = true, onCheckedChange = {})
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        Text("Simulation Lab", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = true, onClick = {}, label = { Text("Music") })
            FilterChip(selected = false, onClick = {}, label = { Text("Notification") })
            FilterChip(selected = false, onClick = {}, label = { Text("Timer") })
        }
    }
}

@Composable
fun PositionTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Position & Size", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        Text("Y Offset", color = Color.White)
        Slider(value = 50f, onValueChange = {}, valueRange = 0f..200f)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Width", color = Color.White)
        Slider(value = 150f, onValueChange = {}, valueRange = 50f..300f)
    }
}

@Composable
fun SettingsTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(24.dp))
        
        SettingItem(title = "Permissions", subtitle = "Setup required access")
        SettingItem(title = "Appearance", subtitle = "Colors and themes")
        SettingItem(title = "Gestures", subtitle = "Configure swipe actions")
    }
}

@Composable
fun SettingItem(title: String, subtitle: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium, color = Color.White)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
