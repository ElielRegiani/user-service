package com.iip.userservice.domain.repository

import com.iip.userservice.domain.model.Preferences

interface PreferencesRepository {
    fun save(preferences: Preferences): Preferences
    fun findByUserId(userId: Long): Preferences?
}
