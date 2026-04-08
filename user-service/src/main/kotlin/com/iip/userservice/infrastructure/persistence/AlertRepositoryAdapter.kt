package com.iip.userservice.infrastructure.persistence

import com.iip.userservice.domain.model.AlertConditionType
import com.iip.userservice.domain.model.AlertConfig
import com.iip.userservice.domain.repository.AlertRepository
import com.iip.userservice.infrastructure.persistence.mapper.AlertMapper
import com.iip.userservice.infrastructure.persistence.repository.AlertJpaRepository
import org.springframework.stereotype.Component

@Component
class AlertRepositoryAdapter(
    private val jpa: AlertJpaRepository,
) : AlertRepository {
    override fun save(alert: AlertConfig): AlertConfig {
        val saved = jpa.save(AlertMapper.toEntity(alert))
        return AlertMapper.toDomain(saved)
    }

    override fun findByUserId(userId: Long): List<AlertConfig> =
        jpa.findByUserId(userId).map(AlertMapper::toDomain)

    override fun existsByUserIdAndSymbolAndConditionType(
        userId: Long,
        symbol: String,
        conditionType: AlertConditionType,
    ): Boolean =
        jpa.existsByUserIdAndSymbolAndConditionType(userId, symbol, conditionType)
}
