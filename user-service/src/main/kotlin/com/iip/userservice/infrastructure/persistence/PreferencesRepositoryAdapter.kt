package com.iip.userservice.infrastructure.persistence

import com.iip.userservice.domain.model.Preferences
import com.iip.userservice.domain.repository.PreferencesRepository
import com.iip.userservice.infrastructure.persistence.mapper.PreferencesMapper
import com.iip.userservice.infrastructure.persistence.repository.PreferencesJpaRepository
import org.springframework.stereotype.Component

@Component
class PreferencesRepositoryAdapter(
    private val jpa: PreferencesJpaRepository,
) : PreferencesRepository {
    override fun save(preferences: Preferences): Preferences {
        val entity =
            jpa.findById(preferences.userId).orElse(null)?.apply {
                riskProfile = preferences.riskProfile
                favoriteAssets.clear()
                favoriteAssets.addAll(preferences.favoriteAssets)
            } ?: PreferencesMapper.toEntity(preferences)
        val saved = jpa.save(entity)
        return PreferencesMapper.toDomain(saved)
    }

    override fun findByUserId(userId: Long): Preferences? =
        jpa.findById(userId).map(PreferencesMapper::toDomain).orElse(null)
}
