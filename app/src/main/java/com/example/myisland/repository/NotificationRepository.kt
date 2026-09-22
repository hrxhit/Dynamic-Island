package com.example.myisland.repository

import com.example.myisland.model.IslandNotification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepository @Inject constructor() {
    private val _activeNotification = MutableStateFlow<IslandNotification?>(null)
    val activeNotification: StateFlow<IslandNotification?> = _activeNotification.asStateFlow()

    fun postNotification(notification: IslandNotification) {
        _activeNotification.value = notification
    }

    fun removeNotification(key: String) {
        if (_activeNotification.value?.key == key) {
            _activeNotification.value = null
        }
    }
}
